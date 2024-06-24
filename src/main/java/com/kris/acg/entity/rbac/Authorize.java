package com.kris.acg.entity.rbac;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description: 角色权限中间类
 * @Author: kris
 * @Create: 2024-06-23 14:16
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authorize {
    Integer id;
    Integer roleId;
    Integer permissionId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public Authorize(Integer roleId, Integer permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}
