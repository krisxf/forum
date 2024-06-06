package com.kris.acg.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 修改密码请求对象封装
 * @Author: kris
 * @Create: 2023-08-13 23:50
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChange {
    @ApiModelProperty("旧密码")
    private String oldPassword;
    @ApiModelProperty("新密码")
    private String newPassword;
}
