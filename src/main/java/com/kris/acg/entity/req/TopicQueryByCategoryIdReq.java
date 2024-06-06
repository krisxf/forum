package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 根据分类id查询相关话题请求对象的封装
 * @Author: kris
 * @Create: 2023-08-18 15:36
 **/

@Data
@NoArgsConstructor
public class TopicQueryByCategoryIdReq {
    @ApiModelProperty("分类id")
    private Integer categoryId;
    @ApiModelProperty("页数")
    private Integer pageNo;
    @ApiModelProperty("大小")
    private Integer pageSize;
    //TODO 添加一个查找时的排序模式
}
