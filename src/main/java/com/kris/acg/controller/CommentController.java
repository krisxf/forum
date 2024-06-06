package com.kris.acg.controller;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kris.acg.common.Constant;
import com.kris.acg.common.PageSizeConstant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.Comment;
import com.kris.acg.entity.community.CommentMsg;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.req.CommentAddReq;
import com.kris.acg.entity.req.CommentModifyReq;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.mq.producer.UserCommentMessageProducer;
import com.kris.acg.service.CommentService;
import com.kris.acg.utils.UserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 评论控制器
 * @Author: kris
 * @Create: 2023-09-06 15:36
 **/

@Api("评论管理")
@RestController
@RequestMapping("/api/community/comments")
public class CommentController {

    @Resource
    CommentService commentService;

    @ApiOperation("查询特定话题的评论")
    @GetMapping("/query/topicComments")
    public Result pageTopicComments(@RequestParam("topicId") Long topicId,
                                    @RequestParam("pageNo") Integer pageNo) {
        //查询话题评论
        List<Comment> comments = commentService.searchTopicComments(topicId);
        PageHelper.startPage(pageNo, PageSizeConstant.TOPIC_COMMENTS);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("添加成功",map);
    }

    @ApiOperation("查询我的所有评论")
    @GetMapping("/query/mine/{pageNo}")
    public Result pageMyComments(@PathVariable("pageNo") Integer pageNo) {
        //查询我评论过的
        Long userId = UserContext.getUserId();
        if(userId == null){
            throw new BusinessException("用户未登陆");
        }
        List<Comment> comments = commentService.searchUserComments(userId);
        PageHelper.startPage(pageNo, PageSizeConstant.TOPIC_COMMENTS);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("添加成功",map);
    }


    @ApiOperation("添加评论")
    @PostMapping("/operate")
    public Result addComment(@RequestBody CommentAddReq commentAddReq){
        //添加评论
        commentService.saveComment(commentAddReq);
        return Result.ok();
    }

    @ApiOperation("修改评论")
    @PutMapping("/operate")
    public Result modifyComment(@Validated @RequestBody CommentModifyReq commentModifyReq){
        //修改评论
        commentService.updateComment(commentModifyReq);
        return Result.ok();
    }


    @ApiOperation("删除评论")
    @DeleteMapping("/operate/{id}")
    public Result deleteComment(@PathVariable("id") Long id){
        //删除评论
        commentService.deleteCommentById(id);
        return Result.ok();
    }

}

