package com.kris.acg.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.kris.acg.common.HeaderKeyConstant;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.service.TokenService;
import com.kris.acg.utils.JwtUtil;
import com.kris.acg.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Program: acg
 * @Description: 过滤器--进行jwt验证
 * @Author: kris
 * @Create: 2023-08-13 16:07
 **/

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(HeaderKeyConstant.AUTHORIZATION);

        try {
            JwtUtil.verifyToken(token);
        } catch (TokenExpiredException e1) {
            Date date = JwtUtil.getExpire(token);
            //过时毫秒数
            long expireMillis = System.currentTimeMillis() - date.getTime();
            //过时限制不超过10s,可以继续解析token
            long boundMillis = 10 * 1000;
            if (expireMillis > boundMillis) {
                return true;
            }
        } catch (JWTVerificationException e2) {
//            return true;
            throw new BusinessException("您未登录");
        }

        if (!tokenService.hasRecord(token)) {
            return true;
        }

        //设置当前用户信息
        UserContext.saveCurrentUserInfo(JwtUtil.getUserId(token), JwtUtil.getUsername(token), JwtUtil.getPhotoUrl(token));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
