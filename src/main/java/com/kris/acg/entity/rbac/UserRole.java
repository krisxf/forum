package com.kris.acg.entity.rbac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description: 用户角色关联实体
 * @Author: kris
 * @Create: 2024-06-23 14:43
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    private Long id;
    private Long userId;
    private Integer roleId;
    private Date createTime;
}

