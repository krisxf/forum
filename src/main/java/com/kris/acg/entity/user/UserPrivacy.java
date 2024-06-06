package com.kris.acg.entity.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description: 用户隐私信息
 * @Author: kris
 * @Create: 2023-08-03 20:41
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrivacy {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String pwd;
    private String salt;
    private String email;
    private Date registerTime;
}
