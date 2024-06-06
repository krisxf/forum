//package com.kris.acg.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
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
// * @Description: 暂无权限的处理类
// * @Author: kris
// * @Create: 2023-09-18 18:03
// **/
//
//
//@Component
//public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        // 设置响应状态为403 Forbidden
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//
//        // 构建一个返回的 JSON 响应
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("status", HttpStatus.FORBIDDEN.value());
//        responseBody.put("message", "Access denied");
//
//        // 将 JSON 响应写入 HttpServletResponse
//        response.setContentType("application/json");
//        PrintWriter writer = response.getWriter();
//        writer.write(new ObjectMapper().writeValueAsString(responseBody));
//        writer.flush();
//    }
//}
