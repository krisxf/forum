package com.kris.acg.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kris.acg.common.Constant;
import com.kris.acg.common.Result;
import com.kris.acg.entity.rbac.Api;
import com.kris.acg.entity.req.ApiAddReq;
import com.kris.acg.entity.req.ApiModifyReq;
import com.kris.acg.entity.req.ApiQueryReq;
import com.kris.acg.service.rbac.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    ApiService apiService;

    @GetMapping("/query/search")
    public Result queryApisBy(@Validated @ModelAttribute ApiQueryReq apiQueryReq){
        String keyword = apiQueryReq.getKeyword();
        Integer pageNo = apiQueryReq.getPageNo();
        Integer pageSize = apiQueryReq.getPageSize();
        PageHelper.startPage(pageNo,pageSize);
        //查询话题
        List<Api> apis = apiService.queryApiByKeyWord(keyword);
        //分页
        PageInfo<Api> pageInfo = new PageInfo<>(apis);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("apis",pageInfo);
        return Result.ok("查询成功",map);
    }


    @PostMapping("/operate")
    public Result addApi(@Validated @RequestBody ApiAddReq apiAddreq){
        apiService.addApi(apiAddreq);
        return Result.ok();
    }

    @PutMapping("/operate")
    public Result modifyApi(@Validated @RequestBody ApiModifyReq apiModifyReq){
        apiService.updateApi(apiModifyReq);
        return Result.ok();
    }

    @DeleteMapping("/operate/{id}")
    public Result deleteApi(@PathVariable int id){
        apiService.deleteApi(id);
        return Result.ok();
    }
}
