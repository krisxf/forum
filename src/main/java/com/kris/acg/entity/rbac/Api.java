package com.kris.acg.entity.rbac;

import com.kris.acg.enums.MyHttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-06 14:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api {
    public static final String PATH_PATTERN="(/([a-zA-Z0-9]+|(\\*){1,2}))+";

    Integer id;
    String path;

    MyHttpMethod method;

    Integer permissionId;
    String note;
    boolean enable;
    Date createTime;
    Date updateTime;
    boolean deleted;
    Permission permission;
}
