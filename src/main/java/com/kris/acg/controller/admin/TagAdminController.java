package com.kris.acg.controller.admin;

import com.kris.acg.common.Result;
import com.kris.acg.entity.req.CategoryModifyReq;
import com.kris.acg.entity.req.TagModifyReq;
import com.kris.acg.service.CategoryService;
import com.kris.acg.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Program: acg
 * @Description: 标签管理控制器
 * @Author: kris
 * @Create: 2024-06-04 17:11
 **/

@RestController
@Slf4j
@RequestMapping("/api/admin/tags/operate")
public class TagAdminController {

    @Resource
    TagService tagService;

    @PostMapping("/add")
    public Result addTag(@RequestParam String label){
        tagService.addTag(label);

        return Result.ok();
    }

    @PostMapping("/modify")
    public Result modifyTag(@ModelAttribute TagModifyReq req){
        tagService.updateTag(req);

        return Result.ok();
    }

    @DeleteMapping("/{id}")
    public Result deleteTag(@PathVariable Integer id ){
        tagService.deleteTagById(id);

        return Result.ok();
    }
}
