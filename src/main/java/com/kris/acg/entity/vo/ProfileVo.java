package com.kris.acg.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Program: acg
 * @Description: 用户信息
 * @Author: kris
 * @Create: 2023-08-13 16:18
 **/

@Data
public class ProfileVo {
    @ApiModelProperty("用户名")
    String username;
    @ApiModelProperty("邮箱")
    String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("注册时间")
    Date registerTime;
    @ApiModelProperty("话题数量")
    Integer topicCount;
    @ApiModelProperty("点赞数")
    Integer starCount;
}
