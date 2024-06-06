package com.kris.acg.controller.admin;

import com.kris.acg.common.Result;
import com.kris.acg.entity.req.UserLogin;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.service.LoginService;
import com.kris.acg.service.TokenService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 管理员登录
 * @Author: kris
 * @Create: 2024-05-24 22:46
 **/

@Api("管理员登录")
@RestController
@Slf4j
@RequestMapping("/api/admin/logins")
public class AdminLoginController {
    @Autowired
    TokenService tokenService;

    @Autowired
    LoginService loginService;



    @PostMapping("/doLogin")
    public Result doLogin(@Validated @RequestBody UserLogin adminLoginReq, HttpServletRequest request) {
        //判断验证码
        String code = adminLoginReq.getVerifyCode();
        loginService.checkImageCode(request,code);
        String email = adminLoginReq.getEmail();
        String password = adminLoginReq.getPassword();
        if("root".equals(email) && "root".equals(password)){
            // 返回信息
            Map<String,Object> map = new HashMap<>();
            map.put("token",tokenService.createToken(1L,"root","root"));
            return Result.ok("登录成功",map);
        }else{
            throw new BusinessException("账号错误！");
        }

    }
}
