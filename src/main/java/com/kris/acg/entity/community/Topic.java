package com.kris.acg.entity.community;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.kris.acg.entity.user.UserBasic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Program: acg
 * @Description: 文章类
 * @Author: kris
 * @Create: 2023-08-14 20:05
 **/

@Data
@NoArgsConstructor
public class Topic {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("作者id")
    private Long userId;

    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("点赞数")
    private Integer starCount = 0;
    @ApiModelProperty("浏览量")
    private Integer pageView = 0;
    @ApiModelProperty("评论数")
    private Integer commentCount = 0;
    @ApiModelProperty("封面图片url")
    private String coverImageUrl;
    @ApiModelProperty("分类id")
    private Integer categoryId;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("活跃时间")
    private Date activityTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("是否被删除")
    boolean deleted;
    @ApiModelProperty("作者的信息")
    UserBasic user;
    @ApiModelProperty("分类的信息")
    Category category;
    @ApiModelProperty("标签id列表")
    List<Integer> tagIds;
    @ApiModelProperty("评论列表")
    List<Comment> comments;
}
