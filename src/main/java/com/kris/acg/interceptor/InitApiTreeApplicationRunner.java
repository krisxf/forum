package com.kris.acg.interceptor;

import com.kris.acg.entity.rbac.ApiTree;
import com.kris.acg.entity.rbac.ApiTreeFactory;
import com.kris.acg.service.rbac.ApiService;
import com.kris.acg.utils.ApiTreeUtils;
import org.simpleframework.xml.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Program: acg
 * @Description: 项目启动后的初始化api树逻辑
 * @Author: kris
 * @Create: 2024-06-12 18:56
 **/

@Component
public class InitApiTreeApplicationRunner implements ApplicationRunner {

    @Autowired
    ApiService apiService;
    @Autowired
    ApiTreeFactory apiTreeFactory;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        ApiTree apiTree = ApiTreeUtils.buildApiTreeFrom(apiService.queryApiByKeyWord(null));
        apiTreeFactory.setApiTree(apiTree);
    }
}
