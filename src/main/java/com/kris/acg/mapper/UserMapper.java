package com.kris.acg.mapper;

import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.entity.user.UserPrivacy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户的基本信息
     * @return 返回
     */
    List<UserBasic>selectAllUserBasicMsg();

    /**
     * 给话题的点赞叠加
     * @param increment 叠加量
     * @param userId 话题id
     * @return nothing
     */
    int updateTopic(Long userId,int increment);

    /**
     * 给用户的点赞叠加
     * @param increment 叠加量
     * @param userId 用户id
     * @return 返回
     */
    int updateStar(Long userId,int increment);

    /**
     * 根据某种排序方式查询用户
     * @param column 排序方式
     * @return 返回用户的集合
     */
    List<UserBasic> selectTopicBySortBy(String column);

    /**
     * 根据用户id查询用户基本信息
     * @param id 用户id
     * @return 返回用户基本信息对象
     */
    UserBasic selectUserBasicInfoById(Long id);

    /**
     * 查询用户隐私信息
     * @param id 用户id
     * @return 返回用户隐私信息对象
     */
    UserPrivacy selectUserPrivacyInfoById(Long id);

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 返回用户信息
     */
    UserBasic selectUserByUsername(String username);

    /**
     * 根据邮箱查询用户信息
     * @param email 邮箱
     * @return 返回用户信息
     */
    UserPrivacy selectUserByEmail(String email);

    /**
     * 插入用户基本信息
     * @param userBasic 用户基本信息对象
     * @return 返回
     */
    int insertUserBasicInfo(UserBasic userBasic);

    /**
     * 插入用户隐私信息
     * @param userPrivacy 用户隐私信息对象
     * @return 返回
     */
    int insertUserPrivacyInfo(UserPrivacy userPrivacy);


    /**
     * 根据id删除用户基本信息
     * @param id 用户id
     * @return 返回
     */
    int deleteUserBasicInfoById(Long id);

    /**
     * 根据id删除用户隐私信息
     * @param id 用户id
     * @return 返回
     */
    int deleteUserPrivacyInfoById(Long id);


    /**
     * 更新用户基本信息
     * @param userBasic 用户基本信息
     * @return 返回
     */
    int updateUserBasicInfo(UserBasic userBasic);

    /**
     * 更新用户隐私信息
     * @param userPrivacy 用户隐私信息对象
     * @return 返回
     */
    int updateUserPrivacyInfo(UserPrivacy userPrivacy);
}
