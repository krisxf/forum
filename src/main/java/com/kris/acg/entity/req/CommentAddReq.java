package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-06 15:41
 **/
@Data
public class CommentAddReq {
    @ApiModelProperty("话题id")
    private Long topicId;
    @ApiModelProperty("评论内容")
    private String content;
}
