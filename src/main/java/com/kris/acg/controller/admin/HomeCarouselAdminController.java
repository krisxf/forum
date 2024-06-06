package com.kris.acg.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.kris.acg.common.Result;
import com.kris.acg.entity.community.HomeCarousel;
import com.kris.acg.entity.req.CarouselAddReq;
import com.kris.acg.entity.req.CarouselModifyReq;
import com.kris.acg.service.HomeCarouselService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: acg
 * @Description: 轮播器中图片管理
 * @Author: kris
 * @Create: 2024-05-26 18:37
 **/

@RestController
@Slf4j
@RequestMapping("/api/admin/homeCarousels")
public class HomeCarouselAdminController {

    @Autowired
    HomeCarouselService homeCarouselService;

    @PostMapping
    public Result addImage(@RequestParam("image") MultipartFile imageFile,
                            @ModelAttribute CarouselAddReq carouselAddReq) {
        homeCarouselService.save(imageFile, carouselAddReq);
        return Result.ok();
    }


    @DeleteMapping("/{id}")
    public Result deleteImage(@PathVariable("id") Long id) {
        homeCarouselService.delete(id);
        return Result.ok();
    }

    @PutMapping("/descriptionAndLink")
    public Result modifyDescription(@ModelAttribute CarouselModifyReq carouselModifyReq) {
        homeCarouselService.update(carouselModifyReq);
        return Result.ok();
    }

    @PostMapping("/cover")
    public Result coverImageFile(@RequestParam("id")  Long id,
                                 @RequestParam("image") MultipartFile imageFile) {
        homeCarouselService.coverHomeCarouselImage(id,imageFile);
        return Result.ok();
    }

    /**
     * 查询现在正在轮播的图片
     * @return 返回
     */
    @GetMapping("/current")
    public Result queryCurrentHomeCarousel() {
        List<HomeCarousel> homeCarousels = homeCarouselService.queryCurrent();
        for (HomeCarousel h: homeCarousels
             ) {
            h.setUrl("http://175.178.109.77:9000" + h.getUrl());
        }
        // 返回信息
        Map<String,Object> map = new HashMap<>();
        map.put("data",homeCarousels);
        return Result.ok("查询成功",map);
    }
}
