package com.kris.acg.mapper;

import com.kris.acg.entity.community.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface CommentMapper {

    /**
     * 查询所有评论
     * @return 返回
     */
    List<Comment> selectAllComments();

    /**
     * 查询用户所发布的所有评论，按时间倒序排
     * @param userId 用户id
     * @return 返回评论集合
     */
    List<Comment> selectUserComments(Long userId);

    /**
     * 查询话题下所有的评论，按时间倒序排
     * @param topicId 话题id
     * @return 返回评论对象集合
     */
    List<Comment> selectTopicComments(Long topicId);

    /**
     * 根据id查询评论
     * @param commentId 评论id
     * @return 返回评论对象
     */
    Comment selectCommentById(Long commentId);

    /**
     * 查询话题下评论的总数
     * @param topicId 话题的id
     * @return 返回
     */
    int selectCountCommentsByTopicId(Long topicId);

    /**
     * 修改评论内容
     * @param comment 评论对象
     * @return 返回
     */
    int updateCommentById(Comment comment);

    /**
     * 添加评论
     * @param comment 评论对象
     * @return 返回
     */
    int insertComment(Comment comment);

    /**
     * 删除评论
     * @param commentId 评论id
     * @return 返回
     */
    int deleteCommentById(Long commentId);

    /**
     * 删除话题下的所有评论
     * @param topicId 话题id
     * @return 返回
     */
    int deleteCommentByTopicId(Long topicId);
}
