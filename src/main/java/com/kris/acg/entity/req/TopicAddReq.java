package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 添加话题请求参数封装
 * @Author: kris
 * @Create: 2023-09-02 14:39
 **/

@Data
@NoArgsConstructor
public class TopicAddReq {
    @ApiModelProperty("话题标题")
    private String title;
    @ApiModelProperty("话题内容")
    private String html;

    @ApiModelProperty("属于哪个分类")
    Integer CategoryId;
    @ApiModelProperty("有哪几个标签")
    Integer[] tagIds;
}