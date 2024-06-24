package com.kris.acg.entity.rbac;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description: 角色实体类
 * @Author: kris
 * @Create: 2024-06-23 14:15
 **/

@Data
@NoArgsConstructor
public class Role {

    Integer id;
    String roleName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    String note;

}
