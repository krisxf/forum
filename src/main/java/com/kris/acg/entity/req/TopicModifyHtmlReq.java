package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-06 14:03
 **/

@Data
@NoArgsConstructor
public class TopicModifyHtmlReq {
    @ApiModelProperty("话题id")
    Long id;
    @ApiModelProperty("修改之后的内容")
    private String html;
}