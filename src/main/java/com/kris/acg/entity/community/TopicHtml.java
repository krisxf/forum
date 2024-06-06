package com.kris.acg.entity.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-19 20:35
 **/

@NoArgsConstructor
@Data
public class TopicHtml {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long topicId;
    String html;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    boolean deleted;

}
