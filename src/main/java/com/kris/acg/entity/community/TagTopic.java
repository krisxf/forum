package com.kris.acg.entity.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-17 17:25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagTopic {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("话题的id")
    Long topicId;
    @ApiModelProperty("关联的标签的id")
    Integer tagId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("关系创建的时间")
    private Date createTime;
    @ApiModelProperty("是否被删除了")
    boolean deleted;
}
