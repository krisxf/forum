package com.kris.acg.entity.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.kris.acg.entity.user.UserBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description: 评论实体类
 * @Author: kris
 * @Create: 2023-08-14 20:01
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属话题id")
    private Long topicId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("评论者id")
    private Long userId;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("是否删除")
    private boolean deleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("话题具体信息")
    Topic topic;
    @ApiModelProperty("评论者具体信息")
    UserBasic user;
}
