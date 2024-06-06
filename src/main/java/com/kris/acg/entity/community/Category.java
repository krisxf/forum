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
 * @Description: 分类实体
 * @Author: kris
 * @Create: 2023-08-17 15:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @ApiModelProperty("id")
    Integer id;
    @ApiModelProperty("分类名称")
    String label;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("是否删除")
    boolean deleted;
}
