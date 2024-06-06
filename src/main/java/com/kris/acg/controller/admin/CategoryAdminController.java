package com.kris.acg.controller.admin;

import com.kris.acg.common.Result;
import com.kris.acg.entity.community.Category;
import com.kris.acg.entity.req.CategoryModifyReq;
import com.kris.acg.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Program: acg
 * @Description: 分类管理控制器
 * @Author: kris
 * @Create: 2024-05-29 21:47
 **/

@RestController
@Slf4j
@RequestMapping("/api/admin/categories/operate")
public class CategoryAdminController {

    @Resource
    CategoryService categoryService;

    @PostMapping("/add")
    public Result addCategory(@RequestParam String label){
        categoryService.addCategory(label);

        return Result.ok();
    }

    @PostMapping("/modify")
    public Result modifyCategory(@ModelAttribute CategoryModifyReq req){
        categoryService.updateCategory(req);

        return Result.ok();
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id ){
        categoryService.deleteCategoryById(id);

        return Result.ok();
    }

}
