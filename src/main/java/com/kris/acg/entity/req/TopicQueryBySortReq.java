package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 根据排序类型查询话题的封装对象
 * @Author: kris
 * @Create: 2023-08-18 22:50
 **/

@Data
@NoArgsConstructor
public class TopicQueryBySortReq {
    @ApiModelProperty("页数")
    private Integer pageNo;
    @ApiModelProperty("大小")
    private Integer pageSize;
}
