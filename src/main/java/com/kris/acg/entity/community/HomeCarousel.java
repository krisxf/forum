package com.kris.acg.entity.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description: 首页轮播图
 * @Author: kris
 * @Create: 2023-08-19 15:28
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeCarousel {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("图片的描述")
    private String description;
    @ApiModelProperty("图片的url")
    private String url;
    @ApiModelProperty("链接到哪篇文章")
    private String link;
    @ApiModelProperty("第几张图")
    private Integer location;
    @ApiModelProperty("图片的大小")
    private Integer size;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private Date updateTime;

}
