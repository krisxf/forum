package com.kris.acg.entity.req;

import com.kris.acg.enums.MyHttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: api添加请求封装类
 * @Author: kris
 * @Create: 2024-05-28 22:31
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiAddReq {

    String path;
    MyHttpMethod method;
    Integer permissionId;

    String note;
    boolean enable;

}
