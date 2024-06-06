package com.kris.acg.controller.admin;

import com.kris.acg.common.Result;
import com.kris.acg.entity.admin.WebsiteDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 网站数据控制器
 * @Author: kris
 * @Create: 2024-05-26 17:13
 **/


@RestController
@Slf4j
@RequestMapping("/api/admin/websites")
public class WebsiteDataController {

    @GetMapping("/accessData")
    public Result queryWebsiteData() {
        WebsiteDataVo web = new WebsiteDataVo();
        web.setPageView(1000);
        web.setApiAccessCount(10000);
        // 返回信息
        Map<String,Object> map = new HashMap<>();
        map.put("data",web);
        return Result.ok("查询成功",map);
    }
}
