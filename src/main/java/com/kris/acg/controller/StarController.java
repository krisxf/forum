package com.kris.acg.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kris.acg.common.Constant;
import com.kris.acg.common.PageSizeConstant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.Star;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.service.StarService;
import com.kris.acg.service.TopicService;
import com.kris.acg.utils.UserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 点赞控制器
 * @Author: kris
 * @Create: 2023-09-05 15:03
 **/

@Api("点赞")
@RestController
@RequestMapping("/api/community/stars")
public class StarController {

    @Resource
    StarService starService;
    @Resource
    TopicService topicService;

    @ApiOperation("查询本用户是否已经点过赞了")
    @GetMapping("/query/isStar/{topicId}")
    public Result isStar( @PathVariable("topicId") Long topicId){
        //判断当前用户是否已经点赞了该内容
        Long userId = UserContext.getUserId();
        boolean userStar = starService.isUserStar(userId, topicId);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("isStar",userStar);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("查询我点赞过的")
    @GetMapping("/query/mine/{pageNo}")
    public Result pageMyStars( @PathVariable("pageNo") Integer pageNo) {
        //获取用户已经点赞过的内容
        Long userId = UserContext.getUserId();
        if(userId == null){
            throw new BusinessException("用户未登陆");
        }
        List<Star> stars = starService.queryUserStar(userId);
        PageHelper.startPage(pageNo, PageSizeConstant.STAR_TOPIC);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        PageInfo<Star> pageInfo = new PageInfo<>(stars);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("用户点赞")
    @PostMapping("/operate/{topicId}")
    public Result addStar( @PathVariable("topicId") Long topicId) {
        //点赞
        Long userId = UserContext.getUserId();
        if(userId == null){
            throw new BusinessException("用户未登陆");
        }
        starService.addStar(userId,topicId);
        return Result.ok();
    }

    @ApiOperation("用户取消赞")
    @DeleteMapping("/operate/{topicId}")
    public Result deleteStar( @PathVariable("topicId") Long topicId) {
        //取消赞
        Long userId = UserContext.getUserId();
        if(userId == null){
            throw new BusinessException("用户未登陆");
        }
        starService.deleteUserStar(UserContext.getUserId(),topicId);
        return Result.ok();
    }

}
