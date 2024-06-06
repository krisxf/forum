package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-06 16:01
 **/

@Data
public class CommentModifyReq {
    @ApiModelProperty("评论id")
    Long id;
    @ApiModelProperty("修改后的评论内容")
    String content;
}
