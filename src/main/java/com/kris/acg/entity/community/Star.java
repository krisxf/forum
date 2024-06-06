package com.kris.acg.entity.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description: 点赞实体类
 * @Author: kris
 * @Create: 2023-08-14 20:12
 **/

@Data
@NoArgsConstructor
public class Star {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("点赞者id")
    private Long userId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("被点赞的话题id")
    private Long topicId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("点赞时间")
    private Date createTime;
    @ApiModelProperty("话题的具体信息")
    private Topic topic;

    public Star(Long userId, Long topicId) {
        this.userId = userId;
        this.topicId = topicId;
    }
}
