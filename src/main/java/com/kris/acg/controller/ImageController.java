package com.kris.acg.controller;

import com.kris.acg.common.Constant;
import com.kris.acg.common.Result;
import com.kris.acg.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-09 14:28
 **/

@Api("用户头像管理")
@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Resource
    UserService userService;

    @ApiOperation("上传用户头像")
    @PostMapping("/photos/operate/upload")
    public Result uploadPhoto(@RequestParam("photoFile") MultipartFile photoFile) {
        String token = userService.updatePhotoUrl(photoFile);
        Map<String,Object> map = new HashMap<>(Constant.HASH_MAP_SIZE);
        map.put("token",token);
        return Result.ok("查询成功",map);
    }
}
