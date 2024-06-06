package com.kris.acg.entity.req;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 用户查询请求封装类
 * @Author: kris
 * @Create: 2024-05-28 22:02
 **/

@Data
@NoArgsConstructor
public class UserQueryReq {
    String keyword;
    Integer pageNo;
    Integer pageSize;
}
