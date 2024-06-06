package com.kris.acg.entity.req;

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

    public static final int PATH_MAX = 200;
    public static final int PATH_MIN = 2;
    public static final int NOTE_MAX = 100;

    String path;

}
