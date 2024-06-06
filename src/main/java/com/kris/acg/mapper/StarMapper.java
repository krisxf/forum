package com.kris.acg.mapper;

import com.kris.acg.entity.community.Star;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface StarMapper {
    /**
     * 查询某个用户是否点赞了该文章
     * @param userId 用户id
     * @param topicId 话题id
     * @return 返回
     */
    Star selectUserIsStar(@Param("userId") Long userId, @Param("topicId") Long topicId);

    /**
     * 查询文章该文章有多少个人点赞
     * @param topicId 话题id
     * @return 返回
     */
    List<Star> selectTopicStar(Long topicId);

    /**
     * 查询用户点赞了哪些内容
     * @param userId 用户id
     * @return 返回
     */
    List<Star> selectUserStar(Long userId);

    /**
     * 新增一条点赞信息
     * @param star 对象
     * @return 返回
     */
    int insertStar(Star star);

    /**
     * 删除文章得时候需要把点赞得信息都删除
     * @param topicId 话题id
     * @return 返回
     */
    int deleteTopicStar(Long topicId);

    /**
     * 删除用户对某个话题得点赞信息
     * @param userId 用户id
     * @param topicId 话题id
     * @return 返回
     */
    int deleteUserStar(@Param("userId") Long userId, @Param("topicId") Long topicId);

}
