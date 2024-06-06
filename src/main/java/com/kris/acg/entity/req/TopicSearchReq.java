package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-03 14:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicSearchReq {
    @ApiModelProperty("分类id")
    private Integer categoryId;
    @ApiModelProperty("标签id")
    private Integer tagId;
    @ApiModelProperty("页码")
    private Integer pageNo;
    @ApiModelProperty("关键词")
    private String search;
    @ApiModelProperty("分类模式")
    private String sortMode;
}
