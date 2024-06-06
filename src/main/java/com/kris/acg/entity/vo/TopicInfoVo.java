package com.kris.acg.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.kris.acg.entity.community.Comment;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.user.UserBasic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-19 20:05
 **/

@Data
@NoArgsConstructor
public class TopicInfoVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("点赞数 ")
    private Integer starCount;
    @ApiModelProperty("浏览量")
    private Integer pageView;
    @ApiModelProperty("评论数")
    private Integer commentCount;
    @ApiModelProperty("封面图片url")
    private String coverImageUrl;
    @ApiModelProperty("分类id")
    private Integer categoryId;
    @ApiModelProperty("标签id")
    private List<Integer> tagIds;
    @ApiModelProperty("活跃时间")
    private Date activityTime;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("内容")
    String html;
    @ApiModelProperty("作者信息")
    UserBasic user;
    @ApiModelProperty("评论")
    List<Comment> comments;
    @ApiModelProperty("相似话题")
    List<Topic> similarTopics;

}
