package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 用户登陆请求参数封装
 * @Author: kris
 * @Create: 2023-08-10 18:33
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("验证码")
    private String verifyCode;

}
