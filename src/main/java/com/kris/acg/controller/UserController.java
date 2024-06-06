package com.kris.acg.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kris.acg.anno.IpMax;
import com.kris.acg.common.Constant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.CommentMsg;
import com.kris.acg.entity.req.PasswordChange;
import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.entity.user.UserPrivacy;
import com.kris.acg.entity.vo.ProfileVo;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.service.CommentMsgService;
import com.kris.acg.service.TokenService;
import com.kris.acg.service.UserService;
import com.kris.acg.utils.EncryptionUtil;
import com.kris.acg.utils.UserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 用户操作控制器
 * @Author: kris
 * @Create: 2023-08-12 20:08
 **/

@Api("用户管理")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService userService;
    @Resource
    TokenService tokenService;
    @Resource
    CommentMsgService commentMsgService;

    @ApiOperation("修改用户的昵称")
    @PutMapping("/users/operate/username/{username}")
    public Result updateUsername(@PathVariable String username){
        //去除空格
        username = username.trim();
        //获得现在的对象的id
        Long userId = UserContext.getUserId();
        //查询用户信息
        UserBasic userBasic = userService.searchUserBasicInfoById(userId);
        //将用户名替换
        userBasic.setUsername(username);
        userService.updateUserBasicInfo(userBasic);
        //重新生成token
        String token = tokenService.createToken(userBasic.getId(), userBasic.getUsername(), userBasic.getPhotoUrl());
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("token",token);
        return Result.ok("修改成功",map);
    }

    @IpMax
    @ApiOperation("查询用户的信息")
    @GetMapping("/users/query/me")
    public Result getUserInfo(){
        ProfileVo user = userService.getProfileVo();
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("user",user);
        return Result.ok("查询成功",map);
    }

    @ApiOperation("修改用户的密码")
    @PostMapping("/users/operate/password")
    public Result passwordChange(@RequestBody PasswordChange pwdChange){
        //获得当前用户id
        Long id = UserContext.getUserId();
        //查询用户信息
        UserPrivacy userPrivacy = userService.searchUserPrivacyInfoById(id);
        //比较旧密码是否一致
        String oldPwd = pwdChange.getOldPassword();
        oldPwd = EncryptionUtil.encryption(oldPwd,userPrivacy.getSalt());
        if(!oldPwd.equals(userPrivacy.getPwd())){
            throw new BusinessException("旧密码错误");
        }
        //一致之后将密码替换后更新数据库
        String newPwd = pwdChange.getNewPassword();
        newPwd = EncryptionUtil.encryption(newPwd,userPrivacy.getSalt());
        userPrivacy.setPwd(newPwd);
        userService.updateUserPrivacyInfo(userPrivacy);
        return Result.ok("修改成功");
    }

    @ApiOperation("查询我的评论信息")
    @GetMapping("/messages/query/comments/page/{pageNo}/{pageSize}")
    public Result pageCommentMessages(@PathVariable("pageNo") Integer pageNo,
                                      @PathVariable("pageSize") Integer pageSize) {

        List<CommentMsg> commentMessages = commentMsgService.queryUserCommentMsg();
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<CommentMsg> pageInfo = new PageInfo<>(commentMessages);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("pageInfo",pageInfo);
        return Result.ok("查询成功",map);
    }


}
