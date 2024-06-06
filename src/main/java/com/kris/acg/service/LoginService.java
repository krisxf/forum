package com.kris.acg.service;

import com.kris.acg.entity.req.UserLogin;
import com.kris.acg.entity.req.UserRegister;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kristin
 */
public interface LoginService {
    /**
     * 用户登陆
     * @param userLogin 用户登陆所需信息
     * @return 返回token
     */
    String login(UserLogin userLogin);

    /**
     * 用户注册
     * @param userRegister 用户注册所需信息
     * @return 返回token
     */
    @Transactional(rollbackFor = Exception.class)
    String register(UserRegister userRegister);

    /**
     * 用户登出
     * @param request 请求
     */
    void logout(HttpServletRequest request);

    /**
     * 判断注册的验证码是否正确
     * @param verifyCode 验证码
     * @param email 用户的邮箱
     */
    void checkEmailCode(String email,String verifyCode);

    /**
     * 给用户发送验证码
     * @param email 用户邮箱
     */
    void sendEmailVerifyCode(String email);

    /**
     * 生成登陆时的图片验证码
     * @param request 请求对象
     * @param response 响应对象
     */
    void writeCodeImageToResponseAndRecord(HttpServletRequest request, HttpServletResponse response);

    /**
     * 判断登陆的验证码是否正确
     * @param verifyCode 验证码
     * @param request 请求对象
     */
    void checkImageCode(HttpServletRequest request,String verifyCode);
}
