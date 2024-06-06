package com.kris.acg.service.Imp;

import cn.hutool.core.util.IdUtil;
import com.kris.acg.entity.community.Comment;
import com.kris.acg.entity.community.CommentMsg;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.req.CommentAddReq;
import com.kris.acg.entity.req.CommentModifyReq;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.mapper.CommentMapper;
import com.kris.acg.mapper.TopicMapper;
import com.kris.acg.mq.producer.UserCommentMessageProducer;
import com.kris.acg.service.CommentService;
import com.kris.acg.service.TopicService;
import com.kris.acg.service.UserService;
import com.kris.acg.utils.SensitiveWordUtil;
import com.kris.acg.utils.UserContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-18 15:03
 **/

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;
    @Resource
    TopicMapper topicMapper;
    @Resource
    UserService userService;
    @Resource
    UserCommentMessageProducer userCommentMessageProducer;

    @Override
    public List<Comment> searchAllComments() {
        return commentMapper.selectAllComments();
    }

    @Override
    public List<Comment> searchUserComments(Long userId) {
        List<Comment> comments = commentMapper.selectUserComments(userId);
        for (Comment comment: comments
        ) {
            comment.setTopic(topicMapper.selectTopicById(comment.getTopicId()));
            comment.setUser(userService.searchUserBasicInfoById(comment.getUserId()));
        }
        return comments;
    }

    @Override
    public List<Comment> searchTopicComments(Long topicId) {
        List<Comment> comments = commentMapper.selectTopicComments(topicId);
        for (Comment comment: comments
             ) {
            comment.setTopic(topicMapper.selectTopicById(comment.getTopicId()));
            comment.setUser(userService.searchUserBasicInfoById(comment.getUserId()));
        }
        return comments;
    }

    @Override
    public Comment getComment(Long commentId) {
        return commentMapper.selectCommentById(commentId);
    }

    @Override
    public int getCommentCount(Long topicId) {
        return commentMapper.selectCountCommentsByTopicId(topicId);
    }

    @Override
    public void updateComment(CommentModifyReq commentModifyReq) {
        //修改评论
        Long id = commentModifyReq.getId();
        String content = commentModifyReq.getContent();

        //获取评论对象
        Comment comment = commentMapper.selectCommentById(id);

        //过滤
        content = SensitiveWordUtil.filter(content);

        Date date = new Date();
        comment.setUpdateTime(date);
        comment.setContent(content);

        commentMapper.updateCommentById(comment);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveComment(CommentAddReq commentAddReq) {
        //添加评论
        String content = commentAddReq.getContent();
        Long topicId = commentAddReq.getTopicId();

        //过滤敏感词
        content = SensitiveWordUtil.filter(content);

        //创建评论对象
        Comment comment = new Comment();
        Date date = new Date();
        // 生成id
        long commentId = IdUtil.getSnowflakeNextId();
        //获取用户id
        Long userId = UserContext.getUserId();
        comment.setContent(content);
        comment.setCreateTime(date);
        comment.setId(commentId);
        comment.setTopicId(topicId);
        comment.setUpdateTime(date);
        comment.setUserId(userId);
        //添加评论
        int incr = commentMapper.insertComment(comment);
        //更新话题的评论数量以及活跃时间
        topicMapper.updateComment(topicId,incr);
        topicMapper.updateActivityTimeInt(topicId,date);

        //向作者发送信息
        CommentMsg commentMsg = new CommentMsg();
        commentMsg.setCommentId(commentId);
        commentMsg.setContent(content);
        commentMsg.setCreateTime(date);
        commentMsg.setFromUserId(userId);
        commentMsg.setTopicId(topicId);
        Topic topic = topicMapper.selectTopicById(topicId);
        commentMsg.setToUserId(topic.getUserId());
        commentMsg.setTopicTitle(topic.getTitle());
        userCommentMessageProducer.sendCommentMessage(commentMsg);
    }

    @Override
    public void deleteCommentById(Long commentId) {
        //获取评论对象
        Comment comment = commentMapper.selectCommentById(commentId);

        if(comment == null){
            throw new BusinessException("这条评论不存在");
        }

        if(!comment.getUserId().equals(UserContext.getUserId())){
            throw new BusinessException("你没有权限删除这条评论");
        }

        //删除评论
        int incr = commentMapper.deleteCommentById(commentId);

        incr = -incr;
        //更新话题的评论数量以及活跃时间
        topicMapper.updateComment(comment.getTopicId(),incr);

    }

    @Override
    public int deleteCommentByTopicId(Long topicId) {
        return commentMapper.deleteCommentByTopicId(topicId);
    }
}
