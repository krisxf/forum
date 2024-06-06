package com.kris.acg.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kris.acg.common.Constant;
import com.kris.acg.common.PageSizeConstant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.enums.SortAttribute;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-20 19:40
 **/

@Api("用户排行榜")
@RestController
@RequestMapping("/api/community/users/rank")
public class UserRankController {

    @Resource
    UserService userService;

    @ApiOperation("根据点赞数或话题数对用户进行排序")
    @GetMapping("/byColumn")
    public Result rankByTopicCount(@RequestParam  Integer pageNo,
                                   @RequestParam String column) {
        switch (column){
            case "starCount" : column = "star_count";break;
            case "topicCount" :  column = "topic_count";break;
            default:
                throw new BusinessException("非法字段");
        }
        List<UserBasic> user = userService.selectTopicBySortBy(column);
        PageHelper.startPage(1, PageSizeConstant.USER_RANK);
        PageInfo<UserBasic> pageInfo = new PageInfo<>(user);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }
}
