package com.kris.acg;

import com.kris.acg.utils.MinioUtil;
import io.minio.*;
import io.minio.errors.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinioTest {

    @Resource
    MinioUtil minioUtil;

    @Test
    public void minioTest() {

    }
}