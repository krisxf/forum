package com.kris.acg.controller;

import com.kris.acg.common.Result;
import com.kris.acg.utils.MinioUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Program: acg
 * @Description: 示例
 * @Author: kris
 * @Create: 2023-08-03 17:30
 **/

@RestController
@Slf4j
public class DemoController {

    @Autowired
    MinioUtil minioUtil;

    @RequestMapping("/")
    public void demo(){
        System.out.println("hello world");

    }
    /**
     * 上传材料
     */
    @PostMapping("/upload")
    public String upload(
            MultipartFile file) throws IOException {
        log.info("uploadFile:上传文件[file:{}]", file);
        String url = minioUtil.upload(file.getInputStream(), "my-file", file.getOriginalFilename());
        System.out.println(url);
        minioUtil.deleteFile(url);
        return "";
    }
}
