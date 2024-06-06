package com.kris.acg.controller;

import com.kris.acg.common.Constant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.Category;
import com.kris.acg.service.CategoryService;
import com.kris.acg.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 评论操作控制器
 * @Author: kris
 * @Create: 2023-08-18 15:17
 **/

@Api("分类管理")
@RestController
@RequestMapping("/api/community/categories")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    @ApiOperation("查询所有分类")
    @GetMapping("/query/all")
    public Result queryAll(){
        List<Category> categories = categoryService.queryAllCategory();
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("categories",categories);
        return Result.ok("查询成功",map);
    }
}
