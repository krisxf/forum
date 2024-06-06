package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 封装用户的请求参数
 * @Author: kris
 * @Create: 2023-08-04 16:24
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegister {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("验证码")
    private String verifyCode;
}
