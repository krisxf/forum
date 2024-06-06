package com.kris.acg.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kris.acg.common.Constant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.Category;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.req.TopicQueryReq;
import com.kris.acg.enums.SortAttribute;
import com.kris.acg.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 文章管理控制器
 * @Author: kris
 * @Create: 2024-05-28 16:11
 **/


@RestController
@Slf4j
@RequestMapping("/api/admin/topics")
public class TopicAdminController {

    @Resource
    TopicService topicService;

    @GetMapping("/query/orderBy")
    public Result listOrderBy(@ModelAttribute TopicQueryReq topicQueryReq) {
        Integer pageNo = topicQueryReq.getPageNo();
        Integer pageSize = topicQueryReq.getPageSize();
        String column = topicQueryReq.getColumn();

        PageHelper.startPage(pageNo,pageSize);
        //查询话题
        List<Topic> topics = new ArrayList<>();
        if(SortAttribute.COMMENT_COUNT.getValue().equals(column)){
            topics = topicService.queryTopicBySortAttribute(SortAttribute.COMMENT_COUNT);
        }else if(SortAttribute.PAGE_VIEW.getValue().equals(column)){
            topics = topicService.queryTopicBySortAttribute(SortAttribute.PAGE_VIEW);
        }if(SortAttribute.CREATE_TIME.getValue().equals(column)){
            topics = topicService.queryTopicBySortAttribute(SortAttribute.CREATE_TIME);
        }if(SortAttribute.ACTIVITY_TIME.getValue().equals(column)){
            topics = topicService.queryTopicBySortAttribute(SortAttribute.ACTIVITY_TIME);
        }if(SortAttribute.CREATE_TIME.getValue().equals(column)){
            topics = topicService.queryTopicBySortAttribute(SortAttribute.CREATE_TIME);
        }

        //分页
        PageInfo<Topic> pageInfo = new PageInfo<>(topics);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }

    @DeleteMapping("/operate/{topicId}")
    public Result delete(@PathVariable  Long topicId){
        topicService.deleteTopicById(topicId);
        return Result.ok();
    }
}
