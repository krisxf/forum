package com.kris.acg.entity.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 用户基本信息
 * @Author: kris
 * @Create: 2023-08-03 20:38
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasic {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("用户名")
    String username;
    @ApiModelProperty("头像url")
    String photoUrl;
    @ApiModelProperty("话题数")
    int topicCount;
    @ApiModelProperty("点赞数")
    int starCount;

    UserBasic(Long id,String username){
        this.id = id;
        this.username = username;
    }

}
