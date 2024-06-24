package com.kris.acg.entity.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: api查询请求封装 类
 * @Author: kris
 * @Create: 2024-05-28 22:30
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiQueryReq {
    String keyword;

    Integer pageNo;

    Integer pageSize;
}
