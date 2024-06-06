package com.kris.acg.mapper;

import com.kris.acg.entity.community.CommentMsg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-14 14:14
 **/

@Mapper
public interface CommentMsgMapper {
    /**
     * 查询所有评论本用户的信息
     * @param userId 用户id
     * @return 返回
     */
    List<CommentMsg> selectUserCommentMsg(Long userId);

    /**
     * 插入一条信息
     * @param commentMsg 评论信息
     * @return 返回
     */
    int insertCommentMsg(CommentMsg commentMsg);
}
