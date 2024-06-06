package com.kris.acg.entity.req;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 查询文章封装类
 * @Author: kris
 * @Create: 2024-05-28 16:16
 **/

@Data
@NoArgsConstructor
public class TopicQueryReq {

    String column;


    Integer pageNo;

    Integer pageSize;
}
