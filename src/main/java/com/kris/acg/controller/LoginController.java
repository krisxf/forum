package com.kris.acg.controller;

import com.kris.acg.common.Constant;
import com.kris.acg.common.KeyNames;
import com.kris.acg.common.Result;
import com.kris.acg.entity.req.UserLogin;
import com.kris.acg.entity.req.UserRegister;
import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.entity.user.UserPrivacy;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.service.LoginService;
import com.kris.acg.service.UserService;
import com.kris.acg.utils.RedisUtil;
import com.kris.acg.utils.UserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 登陆控制器
 * @Author: kris
 * @Create: 2023-08-10 22:03
 **/

@Api("登陆接口")
@RestController
@RequestMapping("/api/user/logins")
public class LoginController {

    @Resource
    LoginService loginService;
    @Resource
    UserService userService;
    @Resource
    RedisUtil redisUtil;

    @ApiOperation("判断用户名是否存在")
    @GetMapping("/isExist/{username}")
    public Result isExist(@PathVariable String username){
        boolean userNameExits = userService.isUserNameExits(username);
        if(userNameExits){
            return Result.error("用户名已存在");
        }
        return Result.ok("");
    }

    @ApiOperation("发送验证码")
    @PostMapping("/sendCode")
    public Result sendCode(@RequestParam("email") String email){
        loginService.sendEmailVerifyCode(email);
        return Result.ok("发送验证码成功");
    }

    @ApiOperation("用户注册")
    @PostMapping("/doRegister")
    public Result doRegister(@RequestBody UserRegister userRegister){
        String username = userRegister.getUsername();
        String email = userRegister.getEmail();
        //检查激活码是否正确
        loginService.checkEmailCode(email,userRegister.getVerifyCode());
        //检查用户名是否存在
        UserBasic userBasic = userService.searchUserByUsername(username);
        if(userBasic != null){
            return Result.error("用户名已存在");
        }
        //检查邮箱是否存在
        UserPrivacy userPrivacy = userService.searchUserByEmail(email);
        if(userPrivacy != null){
            return Result.error("用户邮箱已存在");
        }
        String token = loginService.register(userRegister);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("token",token);
        return Result.ok("注册成功",map);
    }

    @ApiOperation("用户登陆")
    @PostMapping("/doLogin")
    public Result doLogin(HttpServletRequest request,
                          @RequestBody UserLogin userLogin){
        String code = userLogin.getVerifyCode();
        loginService.checkImageCode(request,code);
        String token = loginService.login(userLogin);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("token",token);
        return Result.ok("登陆成功",map);
    }

    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        loginService.logout(request);
        return Result.ok();
    }
}
