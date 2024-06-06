package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-01 13:26
 **/

@Data
@NoArgsConstructor
public class TopicQueryByTagIdReq {
    @ApiModelProperty("标签id")
    private Integer tagId;
    @ApiModelProperty("页码")
    private Integer pageNo;
    @ApiModelProperty("大小")
    private Integer pageSize;
}
