package com.kris.acg.controller;

import com.kris.acg.common.Result;
import com.kris.acg.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Program: acg
 * @Description: 验证码
 * @Author: kris
 * @Create: 2023-08-12 19:38
 **/

@Api("验证码验证")
@RestController
@RequestMapping("/api/user/verifies")
public class VerifyCodeController {

    @Resource
    LoginService loginService;

    @ApiOperation("获取图片验证码")
    @GetMapping("/commonCode")
    public Result getImageCode(HttpServletRequest request, HttpServletResponse response){
        loginService.writeCodeImageToResponseAndRecord(request,response);
        return Result.ok();
    }


}
