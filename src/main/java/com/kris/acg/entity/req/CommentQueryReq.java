package com.kris.acg.entity.req;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 评论查询请求封装类
 * @Author: kris
 * @Create: 2024-05-28 21:44
 **/

@Data
@NoArgsConstructor
public class CommentQueryReq {
    Long topicId;
    String keyword;
    Integer pageNo;
    Integer pageSize;
}
