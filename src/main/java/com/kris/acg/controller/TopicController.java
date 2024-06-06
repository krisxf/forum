package com.kris.acg.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kris.acg.common.Constant;
import com.kris.acg.common.PageSizeConstant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.Category;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.req.*;
import com.kris.acg.entity.vo.TopicInfoVo;
import com.kris.acg.enums.SortAttribute;
import com.kris.acg.service.CategoryService;
import com.kris.acg.service.TopicService;
import com.kris.acg.utils.UserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @Program: acg
 * @Description: 话题操作控制器
 * @Author: kris
 * @Create: 2023-08-18 15:33
 **/

@Api("话题管理")
@Slf4j
@RestController
@RequestMapping("/api/community/topics")
public class TopicController {

    @Resource
    TopicService topicService;
    @Resource
    CategoryService categoryService;

    @ApiOperation("查询最近的热点话题")
    @GetMapping("/query/recentHot")
    public Result queryRecentHotTopics() {
        //按照浏览量查询话题
        List<Topic> topics = topicService.queryTopicBySortAttribute(SortAttribute.PAGE_VIEW);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("list",topics);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("根据评论数，点赞数，浏览量来查询文章")
    @GetMapping("/query/hot/3attrs")
    public Result combineHotTopics() {
        List<List<Topic>> list=new ArrayList<>();
        list.add(topicService.queryTopicBySortAttribute(SortAttribute.COMMENT_COUNT));
        list.add(topicService.queryTopicBySortAttribute(SortAttribute.STAR_COUNT));
        list.add(topicService.queryTopicBySortAttribute(SortAttribute.PAGE_VIEW));
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("list",list);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("查询话题的具体信息")
    @GetMapping("/query/info/{id}")
    public Result getTopicInfo(@PathVariable("id") Long id){
        TopicInfoVo topic = topicService.getTopicInfoAndComment(id);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("list",topic);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("查询某分类下的所有话题")
    @GetMapping("/query/byCategoryId")
    public Result queryByCategoryId(@ModelAttribute TopicQueryByCategoryIdReq topicQueryByCategoryIdReq){
        Integer pageNo = topicQueryByCategoryIdReq.getPageNo();
        Integer categoryId = topicQueryByCategoryIdReq.getCategoryId();
        Integer pageSize = topicQueryByCategoryIdReq.getPageSize();
        //检查这个分类是否存在
        Category category = categoryService.queryCategoryById(categoryId);
        if(category == null){
            return Result.error("分类不存在");
        }

        PageHelper.startPage(pageNo,pageSize);
        //查询话题
        List<Topic> topics = topicService.queryTopicByCategoryId(categoryId);
        for (Topic topic : topics) {
            topic.setCategory(category);
        }
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("根据排序方式查询文章")
    @GetMapping("/query/bySort")
    public Result queryTopicBySort(@ModelAttribute TopicQueryBySortReq topicQueryByCategoryIdReq){
        Integer pageNo = topicQueryByCategoryIdReq.getPageNo();
        Integer pageSize = topicQueryByCategoryIdReq.getPageSize();
        List<Topic> topics = topicService.queryAll();
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("根据评论数查询文章")
    @GetMapping("/query/most/commentCount")
    public Result queryMostCommentCountTopics() {
        //按照浏览量查询话题
        List<Topic> topics = topicService.queryTopicBySortAttribute(SortAttribute.COMMENT_COUNT);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("list",topics);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("查询所有本用户的文章")
    @GetMapping("/query/mine/{pageNo}")
    public Result queryMyTopics(@PathVariable("pageNo") int pageNo) {
        List<Topic> topics = topicService.queryUserTopic(UserContext.getUserId());
        PageHelper.startPage(pageNo,PageSizeConstant.ACTIVITY_COMMENTS);
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("给出推荐的话题")
    @GetMapping("/query/recommend")
    public Result queryRecommendedTopics() {
        //TODO 给用户推荐一些话题 从随机属性中获取随机话题
        List<Topic> topics = topicService.queryAll();
        List<Topic> originalList = topics;
        List<Topic> randomSelection = new ArrayList<>(originalList);
        Collections.shuffle(randomSelection);
        int numberOfElementsToSelect = 4;
        randomSelection = randomSelection.subList(0, Math.min(numberOfElementsToSelect, randomSelection.size()));
        //将分类设置进去
        for (Topic topic : randomSelection
                ) {
            topic.setCategory(categoryService.queryCategoryById(topic.getCategoryId()));
        }
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("list",randomSelection);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("查询最近活跃的话题")
    @GetMapping("/query/most/active/{pageNo}/{pageSize}")
    public Result pageActiveTopics( @PathVariable int pageNo, @PathVariable int pageSize) {
        List<Topic> topics = topicService.queryTopicBySortAttribute(SortAttribute.ACTIVITY_TIME);
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("查询某标签下的所有话题")
    @GetMapping("/query/byTagId")
    public Result pageByTagId(@ModelAttribute TopicQueryByTagIdReq queryReq){
        Integer tagId = queryReq.getTagId();
        Integer pageNo = queryReq.getPageNo();
        Integer pageSize = queryReq.getPageSize();
        List<Topic> topics = topicService.queryTopicByTagId(tagId);
        PageHelper.startPage(pageNo,pageSize);
        //TODO 当没有查询到信息的时候会报错，分页错误？
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }


    @ApiOperation("添加话题")
    @PostMapping("/operate")
    public Result addTopic( @ModelAttribute TopicAddReq topicAddReq, @RequestParam("coverImage") MultipartFile coverImage) {
        String title = topicAddReq.getTitle().trim();
        topicAddReq.setTitle(title);
        Long aLong = topicService.saveTopicInfo(topicAddReq, coverImage);
        log.info(String.valueOf(aLong));
        String topicId = String.valueOf(aLong);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("topicId",topicId);
        return Result.ok("添加成功",map);
    }

    @ApiOperation("修改话题的标题")
    @PutMapping("/operate/title/{id}/{title}")
    public Result modifyTitle( @PathVariable Long id,
                               @PathVariable String title) {
        //更新标题
        title = title.trim();
        topicService.updateTitle(id,title);
        return Result.ok();
    }

    @ApiOperation("修改话题的内容")
    @PutMapping("/operate/html")
    public Result modifyHtml(@Validated @RequestBody TopicModifyHtmlReq topicModifyHtmlReq) {
        //修改内容
        topicService.updateContent(topicModifyHtmlReq);
        return Result.ok();
    }

    @ApiOperation("删除话题")
    @DeleteMapping("/operate/{id}")
    public Result deleteTopic( @PathVariable("id") Long id) {
        //删除话题
        topicService.deleteTopicById(id);
        return Result.ok();
    }

    @ApiOperation("根据用户的关键字查询话题")
    @GetMapping("/search/multiSearch")
    public Result searchTopic(@ModelAttribute TopicSearchReq topicSearchReq){
        String search = topicSearchReq.getSearch();
        if(search != null){
            topicSearchReq.setSearch(search.trim());
        }
        PageHelper.startPage(topicSearchReq.getPageNo(),PageSizeConstant.SEARCH_TOPIC);
        List<Topic> topics = topicService.searchTopic(topicSearchReq);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }

}
