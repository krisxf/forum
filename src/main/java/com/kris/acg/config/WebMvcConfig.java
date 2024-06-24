package com.kris.acg.config;

import com.kris.acg.interceptor.IpInterceptor;
import com.kris.acg.interceptor.JwtInterceptor;
import com.kris.acg.interceptor.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Program: acg
 * @Description: 配置
 * @Author: kris
 * @Create: 2023-08-13 16:06
 **/

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    JwtInterceptor jwtInterceptor;
    @Resource
    IpInterceptor ipInterceptor;
    @Resource
    PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> commonExcludePathPatterns = new ArrayList<>(
                Arrays.asList( "/", "/error","/login","/api/user/verifies/commonCode","/register"
                ,"/api/user/logins/*","/swagger-ui.html"));

        // 先进行接口限流
        registry.addInterceptor(ipInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(commonExcludePathPatterns).order(1);
        // 用户认证
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(commonExcludePathPatterns).order(2);
        // 用户认证之后判断是否有访问权限
        registry.addInterceptor(permissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(commonExcludePathPatterns).order(3);

    }
}
