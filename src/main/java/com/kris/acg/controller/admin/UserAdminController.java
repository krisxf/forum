package com.kris.acg.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kris.acg.common.Constant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.Comment;
import com.kris.acg.entity.req.UserQueryReq;
import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 用户管理
 * @Author: kris
 * @Create: 2024-05-28 22:00
 **/

@RestController
@Slf4j
@RequestMapping("/api/admin/users")
public class UserAdminController {

    @Resource
    UserService userService;

    @GetMapping("/query/search")
    public Result  queryUsersBy(@Validated @ModelAttribute UserQueryReq userQueryReq){
        Integer pageNo = userQueryReq.getPageNo();
        Integer pageSize = userQueryReq.getPageSize();
        String keyword = userQueryReq.getKeyword();
        //TODO 根据关键字来查询用户
        List<UserBasic> userBasics = userService.searchAllUserBasicMsg();

        //分页
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<UserBasic> pageInfo = new PageInfo<>(userBasics);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }


    @DeleteMapping("/operate/{userId}/{roleId}")
    public Result deleteRoleForUser(@PathVariable("userId")  Long userId, @PathVariable  Integer roleId) {
        userService.deleteUserBasicInfoById(userId);
        userService.deleteUserPrivacyInfoById(userId);
        return Result.ok();
    }

}
