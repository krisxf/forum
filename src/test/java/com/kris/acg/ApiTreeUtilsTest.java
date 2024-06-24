package com.kris.acg;

import com.kris.acg.entity.rbac.Api;
import com.kris.acg.entity.rbac.ApiTreeFactory;
import com.kris.acg.utils.ApiTreeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-06 15:16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTreeUtilsTest {

    @Autowired
    ApiTreeFactory apiTreeFactory;

    @Test
    public void test(){
        String requestURI = "/api/community/categories/query/all";
        String method = "GET";
        Api api = ApiTreeUtils.getMatchApi(apiTreeFactory.getApiTree(), requestURI, method);
    }

}
