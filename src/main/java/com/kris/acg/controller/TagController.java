package com.kris.acg.controller;

import com.kris.acg.common.Constant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.Category;
import com.kris.acg.entity.community.Tag;
import com.kris.acg.service.TagService;
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
 * @Description: 标签操作控制器
 * @Author: kris
 * @Create: 2023-08-18 15:27
 **/

@Api("标签")
@RestController
@RequestMapping("/api/community/tags")
public class TagController {

    @Resource
    TagService tagService;

    @ApiOperation("查询所有标签")
    @GetMapping("/query/all")
    public Result queryAll(){
        List<Tag> tags = tagService.searchAllTag();
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("tags",tags);
        return Result.ok("查询成功",map);
    }
}
