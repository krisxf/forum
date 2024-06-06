package com.kris.acg.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kris.acg.common.Constant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.Comment;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.req.CommentQueryReq;
import com.kris.acg.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 评论管理器
 * @Author: kris
 * @Create: 2024-05-28 21:41
 **/

@RestController
@Slf4j
@RequestMapping("/api/admin/comments")
public class CommentAdminController {

    @Resource
    CommentService commentService;

    @GetMapping("/query/by")
    public Result pageCommentsBy(@Validated @ModelAttribute CommentQueryReq queryReq) {
        Integer pageNo = queryReq.getPageNo();
        Integer pageSize = queryReq.getPageSize();
        String keyword = queryReq.getKeyword();
        //TODO 根据话题id和关键字来查询评论
        List<Comment> comments = commentService.searchAllComments();

        //分页
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);

    }

    @DeleteMapping("/operate/{id}")
    public Result pageCommentsBy(@PathVariable  Long id) {
        commentService.deleteCommentById(id);
        return Result.ok();
    }
}
