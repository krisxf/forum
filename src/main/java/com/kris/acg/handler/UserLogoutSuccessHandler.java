//package com.kris.acg.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
// * @Description:
// * @Author: kris
// * @Create: 2023-09-18 18:43
// **/
//
//public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        // 设置响应状态为401 Unauthorized
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//
//        // 构建一个返回的 JSON 响应
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
//        responseBody.put("message", "Authentication failed");
//
//        // 将 JSON 响应写入 HttpServletResponse
//        response.setContentType("application/json");
//        PrintWriter writer = response.getWriter();
//        writer.write(new ObjectMapper().writeValueAsString(responseBody));
//        writer.flush();
//    }
//}
