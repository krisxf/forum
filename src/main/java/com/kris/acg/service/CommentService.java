package com.kris.acg.service;

import com.kris.acg.entity.community.Comment;
import com.kris.acg.entity.req.CommentAddReq;
import com.kris.acg.entity.req.CommentModifyReq;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kristin
 */
public interface CommentService {
    /**
     * 查询所有评论
     * @return 返回
     */
    List<Comment> searchAllComments();

    /**
     * 查询用户所发布的所有评论，按时间倒序排
     * @param userId 用户id
     * @return 返回评论集合
     */
    List<Comment> searchUserComments(Long userId);

    /**
     * 查询话题下所有的评论，按时间倒序排
     * @param topicId 话题id
     * @return 返回评论对象集合
     */
    List<Comment> searchTopicComments(Long topicId);

    /**
     * 根据id查询评论
     * @param commentId 评论id
     * @return 返回评论对象
     */
    Comment getComment(Long commentId);

    /**
     * 查询话题下评论的总数
     * @param topicId 话题的id
     * @return 返回
     */
    int getCommentCount(Long topicId);

    /**
     * 修改评论内容
     * @param commentModifyReq 评论封装对象
     */
    void updateComment(CommentModifyReq commentModifyReq);

    /**
     * 添加评论
     * @param commentAddReq 评论请求对象
     */

    void saveComment(CommentAddReq commentAddReq);

    /**
     * 删除评论
     * @param commentId 评论id
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteCommentById(Long commentId);

    /**
     * 删除话题下的所有评论
     * @param topicId 话题id
     * @return 返回
     */
    int deleteCommentByTopicId(Long topicId);
}
