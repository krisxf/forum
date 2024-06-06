//package com.kris.acg.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Program: acg
// * @Description: 登陆失败
// * @Author: kris
// * @Create: 2023-09-18 18:40
// **/
//
//@Component
//public class UserLoginFailureHandler implements AuthenticationFailureHandler {
//        @Override
//        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//            // 设置响应状态为401 Unauthorized
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//
//            // 构建一个返回的 JSON 响应
//            Map<String, Object> responseBody = new HashMap<>();
//            responseBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
//            responseBody.put("message", "Authentication failed");
//
//            // 将 JSON 响应写入 HttpServletResponse
//            response.setContentType("application/json");
//            PrintWriter writer = response.getWriter();
//            writer.write(new ObjectMapper().writeValueAsString(responseBody));
//            writer.flush();
//        }
//}
