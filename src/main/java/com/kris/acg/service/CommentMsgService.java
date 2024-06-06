package com.kris.acg.service;

import com.kris.acg.entity.community.CommentMsg;

import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-14 14:19
 **/

public interface CommentMsgService {
    /**
     * 查询所有评论本用户的信息
     * @return 返回
     */
    List<CommentMsg> queryUserCommentMsg();

    /**
     * 插入一条信息
     * @param commentMsg 评论信息
     */
    void addCommentMsg(CommentMsg commentMsg);
}
