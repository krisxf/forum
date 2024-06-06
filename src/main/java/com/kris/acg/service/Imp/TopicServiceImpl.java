package com.kris.acg.service.Imp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.kris.acg.common.*;
import com.kris.acg.entity.community.Category;
import com.kris.acg.entity.community.Comment;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.community.TopicHtml;
import com.kris.acg.entity.req.TopicAddReq;
import com.kris.acg.entity.req.TopicModifyHtmlReq;
import com.kris.acg.entity.req.TopicSearchReq;
import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.entity.vo.TopicInfoVo;
import com.kris.acg.enums.SortAttribute;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.mapper.*;
import com.kris.acg.service.*;
import com.kris.acg.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-17 18:19
 **/

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    @Resource
    TopicMapper topicMapper;
    @Resource
    CommentService commentService;
    @Resource
    UserService userService;
    @Resource
    TopicHtmlService topicHtmlService;
    @Resource
    TagTopicService tagTopicService;
    @Resource
    CategoryService categoryService;
    @Resource
    StarMapper starMapper;
    @Resource
    MinioUtil minioUtil;
    @Resource
    RedisUtil redisUtil;

    @Override
    public int updateTopicPageView(Long topicId, int increment) {
        return topicMapper.updateTopicPageView(topicId,increment);
    }

    @Override
    public int updateActivityTimeInt(Long topicId, Date activityTime) {
        return topicMapper.updateActivityTimeInt(topicId,activityTime);
    }

    @Override
    public void addComment(Long topicId, int increment) {
        topicMapper.updateComment(topicId,increment);
    }

    @Override
    public void updateContent(TopicModifyHtmlReq topicModifyHtmlReq) {
        Long id = topicModifyHtmlReq.getId();
        String html = topicModifyHtmlReq.getHtml();
        //获取话题对象
        Topic topic = topicMapper.selectTopicById(id);
        //获取话题的作者 判断与当前的用户是否是一个
        Long userId = topic.getUserId();
        if(!userId.equals(UserContext.getUserId())){
            throw new BusinessException("您没有权限修改这篇文章");
        }
        //过滤内容
        String text = HtmlUtil.html2Text(html);
        String content = SensitiveWordUtil.filter(text);
        //设置内容以及更新活跃时间
        topic.setContent(content);
        Date date = new Date();
        topic.setUpdateTime(date);
        topic.setActivityTime(date);
        updateTopic(topic);
        //更新topic_html表中的信息
        TopicHtml topicHtml = topicHtmlService.queryTopicHtmlByTopicId(id);
        topicHtml.setHtml(SensitiveWordUtil.filter(html));
        topicHtml.setUpdateTime(date);
        topicHtmlService.updateTopicHtml(topicHtml);

    }

    @Override
    public void updateTitle(Long topicId, String title) {
        //先获取topic对象
        Topic topic = topicMapper.selectTopicById(topicId);
        //获取话题的作者 判断与当前的用户是否是一个
        Long userId = topic.getUserId();
        if(!userId.equals(UserContext.getUserId())){
            throw new BusinessException("您没有权限修改这篇文章");
        }
        //过滤敏感词
        title = SensitiveWordUtil.filter(title);
        //设置标题以及更新活跃时间
        topic.setTitle(title);
        Date date = new Date();
        topic.setUpdateTime(date);
        topic.setActivityTime(date);

        updateTopic(topic);
    }

    @Override
    public void addStar(Long topicId, int increment) {
        topicMapper.updateStar(topicId,increment);
    }

    @Override
    public List<Topic> searchTopic(TopicSearchReq topicSearchReq) {
        //处理sortMode
        String sortMode = topicSearchReq.getSortMode();
        Integer tagId = topicSearchReq.getTagId();
        //TODO 这种实现方式并不好，当需要改动分类选项得时候还得改动代码，可以试着使用一下设计模式？
        switch (sortMode){
            case "NORMAL": sortMode = SortAttribute.CREATE_TIME.getValue();break;
            case "CREATE_TIME" : sortMode = SortAttribute.CREATE_TIME.getValue();break;
            case "STAR_COUNT" : sortMode = SortAttribute.STAR_COUNT.getValue();break;
            case "COMMENT_COUNT" : sortMode = SortAttribute.COMMENT_COUNT.getValue();break;
            case "PAGE_VIEW" : sortMode = SortAttribute.PAGE_VIEW.getValue();break;
            default:
                throw new BusinessException("没有这种分类选项");
        }
        topicSearchReq.setSortMode(sortMode);
        //查询文章
        List<Topic> topics = topicMapper.selectTopicByReq(topicSearchReq);
        //处理tagId
        if(tagId != null){
            List<Long> topicIds = tagTopicService.selectTopicIds(tagId);
            topics.removeIf(t -> !topicIds.contains(t.getId()));
        }

        return topics;
    }

    @Override
    public Long saveTopicInfo(TopicAddReq topicAddReq, MultipartFile coverImage) {
        Topic topic = new Topic();
        //获取html
        String html = topicAddReq.getHtml();
        //获取title
        String title = SensitiveWordUtil.filter(topicAddReq.getTitle());
        //获取内容
        String content = HtmlUtil.html2Text(topicAddReq.getHtml());
        content = SensitiveWordUtil.filter(content);
        //分类id
        Integer categoryId = topicAddReq.getCategoryId();
        //标签id
        Integer[] tagIds = topicAddReq.getTagIds();

        //生成topicId
        long topicId = IdUtil.getSnowflakeNextId();
        //生成图片的url
        Date now = new Date();
        //获取用户id
        Long userId = UserContext.getUserId();

        //压缩图片为jpg格式
        InputStream inputStream = ImageUtil.compressAsFixedWebpImage(coverImage,
                CoverImageConstants.WIDTH,
                CoverImageConstants.HEIGHT,
                CoverImageConstants.LIMIT_COMPRESS_SIZE);

        String suffix = String.format("%s.%s", topicId, FileFormatConstants.JPG);
        String url = minioUtil.generateBaseUrl(StorePrefixConstants.COVER_IMAGE, now, suffix);
        String coverImageUrl = null;
        coverImageUrl = minioUtil.upload(inputStream, url, FileFormatConstants.JPG_CONTENT_TYPE);

        topic.setCategoryId(categoryId);
        topic.setActivityTime(now);
        topic.setContent(content);
        topic.setCreateTime(now);
        topic.setCoverImageUrl(coverImageUrl);
        topic.setId(topicId);
        topic.setTitle(title);
        topic.setUpdateTime(now);
//        if(UserContext.getUserId() == null){
//            throw new BusinessException("用户未登陆");
//        }
//        UserBasic userBasic = new UserBasic();
//        userBasic.setUsername(UserContext.getUsername());
//        userBasic.setPhotoUrl(UserContext.getPhotoUrl());
//        userBasic.setId(UserContext.getUserId());
//        topic.setUser(userBasic);
        topic.setUserId(userId);

        //保存topic
        topicMapper.insertTopic(topic);
        //保存html
        TopicHtml topicHtml = new TopicHtml();
        topicHtml.setTopicId(topicId);
        topicHtml.setHtml(html);
        topicHtml.setCreateTime(now);
        topicHtml.setUpdateTime(now);
        topicHtmlService.addTopicHtml(topicHtml);
        //保存tagId
        //检查标签是否存在
        List<Integer> noRepeatTagIds = ListUtil.removeRepeat(Arrays.asList(topicAddReq.getTagIds()));
        tagTopicService.save(topicId,noRepeatTagIds);


        //TODO 给该文章的pageView设置缓存
//        String pageViewKey = KeyNames.getPageViewKey(topicId);
//        redisUtil.set(pageViewKey,0);

        //更新排行榜  直接删除
        UserBasic userBasic = userService.searchUserBasicInfoById(userId);
        String str = JSON.toJSONString(userBasic);
        String userRankColumnKey = KeyNames.getUserRankColumnKey(RedisConstant.USER_RANK_TOPIC_COUNT);
        redisUtil.zSSRem(userRankColumnKey,str);


        //给用户的话题数量 + 1
        userService.addTopic(userId,1);
        return topicId;
    }

    @Override
    public List<Topic> queryTopicByTagId(int tagId) {
        //根据标签id查询对应的话题id
        List<Long> topicIds = tagTopicService.selectTopicIds(tagId);
        if(topicIds.isEmpty()){
            return null;
        }
        List<Topic> topics = topicMapper.selectTopicsByTagIds(topicIds);
        for (Topic topic: topics
             ) {
            topic.setCategory(categoryService.queryCategoryById(topic.getCategoryId()));
            topic.setTagIds(tagTopicService.selectTagIds(topic.getId()));
        }
        return topics;
    }

    @Override
    public TopicInfoVo getTopicInfoAndComment(Long topicId) {
        TopicInfoVo topicInfoVo = new TopicInfoVo();
        //获取话题信息
        Topic topic = topicMapper.selectTopicById(topicId);

        if(topic == null){
            throw new BusinessException("话题不存在或已删除");
        }
        BeanUtil.copyProperties(topic, topicInfoVo, false);
        //获取第一页评论信息
        List<Comment> comments = commentService.searchTopicComments(topicId);
        comments.subList(0, Math.min(comments.size(), PageSizeConstant.TOPIC_COMMENTS));
        topicInfoVo.setComments(comments);
        //获取用户信息
        UserBasic userBasic = userService.searchUserBasicInfoById(topic.getUserId());
        topicInfoVo.setUser(userBasic);
        //TODO 还要设置相似话题，相似话题涉及到ES
        TopicHtml topicHtml = topicHtmlService.queryTopicHtmlByTopicId(topicId);
        topicInfoVo.setHtml(topicHtml.getHtml());

        //将缓存中的pageView + 1
        //先判断缓存中是否存在这个key
        //TODO redis缓存浏览量
//        String pageViewKey = KeyNames.getPageViewKey(topicId);
//        boolean b = redisUtil.hasKey(pageViewKey);
//        //如果键存在，将值+1
//        if(b){
//            redisUtil.incr(pageViewKey,1L);
//        }else{
//            //不存在，放入一个k-v   v即为当前的pagView , 过期时间设置为30分钟
//            redisUtil.set(pageViewKey,topic.getPageView(),30 * 60);
//        }
        //对应的topic的pageView + 1  这个任务交给定时任务，统一将浏览量持久化
        topicMapper.updateTopicPageView(topicId,1);
        return topicInfoVo;
    }

    @Override
    public List<Topic> queryTopicBySortAttribute(SortAttribute sortAttribute) {
        List<Topic> topics = topicMapper.selectTopicBySortBy(sortAttribute.getValue());
        for (Topic topic: topics
        ) {
            topic.setCategory(categoryService.queryCategoryById(topic.getCategoryId()));
            topic.setTagIds(tagTopicService.selectTagIds(topic.getId()));
        }
        return topics;
    }

    @Override
    public List<Topic> queryAll() {
        List<Topic> topics = topicMapper.selectAll();
        for (Topic topic: topics
        ) {
            topic.setCategory(categoryService.queryCategoryById(topic.getCategoryId()));
            topic.setTagIds(tagTopicService.selectTagIds(topic.getId()));
        }
        return topics;
    }

    @Override
    public List<Topic> queryTopicByCategoryId(int categoryId) {
        //查询话题
        List<Topic> topics = topicMapper.selectTopicByCategoryId(categoryId);
        Category category = categoryService.queryCategoryById(categoryId);
        for (Topic topic: topics
        ) {
            topic.setCategory(category);
            topic.setTagIds(tagTopicService.selectTagIds(topic.getId()));
        }
        return topics;
    }

    @Override
    public List<Topic> queryUserTopic(Long userId) {
        List<Topic> topics = topicMapper.selectUserTopic(userId);
        for (Topic topic: topics
        ) {
            topic.setCategory(categoryService.queryCategoryById(topic.getCategoryId()));
            topic.setTagIds(tagTopicService.selectTagIds(topic.getId()));
        }
        return topics;
    }

    @Override
    public Topic queryTopicById(Long topicId) {
        return topicMapper.selectTopicById(topicId);
    }

    @Override
    public int queryCountUserTopic(Long userId) {
        return topicMapper.selectCountUserTopic(userId);
    }

    @Override
    public int addTopic(Topic topic) {
        return topicMapper.insertTopic(topic);
    }

    @Override
    public int updateTopic(Topic topic) {
        return topicMapper.updateTopic(topic);
    }

    @Override
    public int deleteTopicById(Long topicId) {
        //获取话题对象
        Topic topic = topicMapper.selectTopicById(topicId);
        //获取话题的作者 判断与当前的用户是否是一个
        Long userId = topic.getUserId();
        if(!userId.equals(UserContext.getUserId())){
            throw new BusinessException("您没有权限修改这篇文章");
        }
        //删除点赞表中的数据
        starMapper.deleteTopicStar(topicId);
        //删除tag_topic表中的数据
        tagTopicService.deleteTagTopic(topicId);
        //删除topic_html表中的数据
        topicHtmlService.deleteTopicHtml(topicId);
        //删除评论
        commentService.deleteCommentByTopicId(topicId);
        //更新排行榜  删除在添加
        UserBasic userBasic = userService.searchUserBasicInfoById(userId);
        String str = JSON.toJSONString(userBasic);
        String userRankColumnKey = KeyNames.getUserRankColumnKey(RedisConstant.USER_RANK_TOPIC_COUNT);
        redisUtil.zSSRem(userRankColumnKey,str);
        userBasic.setTopicCount(userBasic.getTopicCount() -1);
        str = JSON.toJSONString(userBasic);
        redisUtil.zSSet(userRankColumnKey,str,userBasic.getTopicCount());
        //更新用户的话题数
        userService.addTopic(userId,-1);
        return topicMapper.deleteTopicById(topicId);
    }
}
