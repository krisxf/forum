//package com.kris.acg.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
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
// * @Description: 用户未登陆处理类
// * @Author: kris
// * @Create: 2023-09-18 18:10
// **/
//
//@Component
//public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        // 设置响应状态为403 Forbidden
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//
//        // 构建一个返回的 JSON 响应
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("status", HttpStatus.UNAUTHORIZED.value());
//        responseBody.put("message", "未登陆");
//
//        // 将 JSON 响应写入 HttpServletResponse
//        response.setContentType("application/json");
//        PrintWriter writer = response.getWriter();
//        writer.write(new ObjectMapper().writeValueAsString(responseBody));
//        writer.flush();
//    }
//}
