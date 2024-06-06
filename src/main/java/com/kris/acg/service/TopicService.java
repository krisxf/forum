package com.kris.acg.service;

import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.req.TopicAddReq;
import com.kris.acg.entity.req.TopicModifyHtmlReq;
import com.kris.acg.entity.req.TopicSearchReq;
import com.kris.acg.entity.vo.TopicInfoVo;
import com.kris.acg.enums.SortAttribute;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author Kristin
 */
public interface TopicService {

    /**
     * 叠加一个话题的浏览量
     * @param topicId 话题
     * @param increment 增量
     * @return 返回
     */
    int updateTopicPageView(Long topicId,int increment);

    /**
     * 更新话题的活跃时间
     * @param topicId 话题id
     * @param activityTime 活跃时间
     * @return 返回
     */
    int updateActivityTimeInt(Long topicId, Date activityTime);

    /**
     * 给话题的评论叠加
     * @param increment 叠加量
     * @param topicId 话题id
     */
    void addComment(Long topicId,int increment);

    /**
     * 更新话题的内容
     * @param topicModifyHtmlReq 请求参数的封装对象
     */
    @Transactional(rollbackFor = Exception.class)
    void updateContent(TopicModifyHtmlReq topicModifyHtmlReq);

    /**
     * 修改文章的标题
     * @param topicId 话题id
     * @param title 标题
     */
    void updateTitle(Long topicId,String title);

    /**
     * 给话题的点赞叠加
     * @param increment 叠加量
     * @param topicId 话题id
     */
    void addStar(Long topicId,int increment);

    /**
     * 根据查询的参数来查询 话题
     * @param topicSearchReq 查询的参数
     * @return 返回
     */
    List<Topic> searchTopic(TopicSearchReq topicSearchReq);

    /**
     * 添加话题信息
     * @param topicAddReq 话题请求参数
     * @param coverImage 封面图片
     * @return 话题id
     */
    @Transactional(rollbackFor = Exception.class)
    Long saveTopicInfo(TopicAddReq topicAddReq, MultipartFile coverImage);
    /**
     * 根据标签查询对应的话题
     * @param tagId 标签
     * @return 返回
     */
    List<Topic> queryTopicByTagId(int tagId);

    /**
     * 查询话题的详细信息以及评论
     * @param topicId 话题id
     * @return 返回
     */
    TopicInfoVo getTopicInfoAndComment(Long topicId);

    /**
     * 根据排序方式查询话题
     * @param sortAttribute 排序方式
     * @return 返回
     */
    List<Topic> queryTopicBySortAttribute(SortAttribute sortAttribute);

    /**
     * 查询所有的文章
     * @return 返回
     */
    List<Topic> queryAll();

    /**
     * 查询某一分类下的话题
     * @param categoryId 分类id
     * @return 返回
     */
    List<Topic> queryTopicByCategoryId(int categoryId);

    /**
     * 查询用户发布的所有话题
     * @param userId 用户id
     * @return 返回
     */
    List<Topic> queryUserTopic(Long userId);

    /**
     * 根据id查询话题
     * @param topicId 话题id
     * @return 返回
     */
    Topic queryTopicById(Long topicId);

    /**
     * 查询用户发布话题的数量
     * @param userId 用户id
     * @return 返回
     */
    int queryCountUserTopic(Long userId);

    /**
     * 查询话题
     * @param topic 话题对象
     * @return 返回
     */
    int addTopic(Topic topic);

    /**
     * 修改话题
     * @param topic 话题对象
     * @return 返回
     */
    int updateTopic(Topic topic);

    /**
     * 删除话题
     * @param topicId 话题id
     * @return 返回
     */
    @Transactional(rollbackFor = Exception.class)
    int deleteTopicById(Long topicId);
}
