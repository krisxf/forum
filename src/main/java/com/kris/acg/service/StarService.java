package com.kris.acg.service;

import com.kris.acg.entity.community.Star;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-05 14:41
 **/

public interface StarService {
    /**
     * 查询某个用户是否点赞了该文章
     * @param userId 用户id
     * @param topicId 话题id
     * @return 返回
     */
    boolean isUserStar(@Param("userId") Long userId, @Param("topicId") Long topicId);

    /**
     * 查询文章该文章有多少个人点赞
     * @param topicId 话题id
     * @return 返回
     */
    List<Star> queryTopicStar(Long topicId);

    /**
     * 查询用户点赞了哪些内容
     * @param userId 用户id
     * @return 返回
     */
    List<Star> queryUserStar(Long userId);

    /**
     * 新增一条点赞信息
     * @param userId 用户id
     * @param topicId 话题id
     */
    @Transactional(rollbackFor = Exception.class)
    void addStar(Long userId,Long topicId);

    /**
     * 删除文章得时候需要把点赞得信息都删除
     * @param topicId 话题id
     */
    void deleteTopicStar(Long topicId);

    /**
     * 删除用户对某个话题得点赞信息
     * @param userId 用户id
     * @param topicId 话题id
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteUserStar(@Param("userId") Long userId, @Param("topicId") Long topicId);
}
