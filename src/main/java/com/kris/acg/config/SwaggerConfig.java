package com.kris.acg.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-22 18:53
 **/

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //enable 设置是否启动Swagger
                .enable(true)
                //通过.select()方法，去配置扫描接口
                .select()
                //RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.kris.acg.controller"))
                //配置如何通过path过滤，即这里只扫描请求以/api开头的接口
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("koko", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        return new ApiInfo(
                // 标题
                "ACG接口信息",
                // 描述
                "ACG接口信息",
                // 版本
                "v1.0",
                // 组织链接
                "http://terms.service.url/组织链接",
                // 联系人信息
                contact,
                // 许可
                "Apache 2.0 许可",
                // 许可连接
                "许可链接",
                // 扩展
                new ArrayList<>()
        );
    }


}
