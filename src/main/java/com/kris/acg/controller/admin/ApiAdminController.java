package com.kris.acg.controller.admin;

import com.kris.acg.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Program: acg
 * @Description: api控制器
 * @Author: kris
 * @Create: 2024-05-28 22:25
 **/


@RestController
@Slf4j
@RequestMapping("/api/admin/apis")
public class ApiAdminController {

//    @GetMapping("/query/search")
//    public Result queryApisBy(@Validated @ModelAttribute ApiQueryReq apiQueryReq){
//        return Result.ok(apiQueryService.pageBy(apiQueryReq));
//    }
//
//
//    @PostMapping("/operate")
//    public Result addApi(@Validated @RequestBody ApiAddReq apiAddreq){
//
//        return Result.ok();
//    }
//
//    @PutMapping("/operate")
//    public Result modifyApi(@Validated @RequestBody ApiModifyReq apiModifyReq){
//
//        return Result.ok();
//    }
//
//    @DeleteMapping("/operate/{id}")
//    public Result deleteApi(@PathVariable int id){
//
//    }
}
