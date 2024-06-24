package com.kris.acg.entity.rbac;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Program: acg
 * @Description: 权限实体类
 * @Author: kris
 * @Create: 2024-06-06 14:57
 **/

@Data
@NoArgsConstructor
public class Permission {

    Integer id;
    Integer parentId;
    String code;
    String note;
    String label;
    boolean module;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    List<Permission> children;
    Permission parent;
}
