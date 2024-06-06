package com.kris.acg.controller;

import com.kris.acg.common.Constant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.HomeCarousel;
import com.kris.acg.service.HomeCarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-19 15:40
 **/

@Api("首页轮播图")
@RestController
@RequestMapping("/api/community/homeCarousels")
public class HomeCarouselController {

    @Resource
    HomeCarouselService homeCarouselService;

    @ApiOperation("查询首页轮播图")
    @GetMapping("/list")
    public Result getList(){
        List<HomeCarousel> homeCarousels = homeCarouselService.selectAll();
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("homeCarousels",homeCarousels);
        return Result.ok("查询成功",map);
    }
}
