package com.kris.acg.service;

import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.entity.user.UserPrivacy;
import com.kris.acg.entity.vo.ProfileVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Kristin
 */
public interface UserService {

    /**
     * 查询所有用户的基本信息
     * @return 返回
     */
    List<UserBasic>searchAllUserBasicMsg();

    /**
     * 给话题的点赞叠加
     * @param increment 叠加量
     * @param userId 话题id
     */
    void addTopic(Long userId,int increment);

    /**
     * 修改用户的头像
     * @param multipartFile 头像文件
     * @return 返回token
     */
    String updatePhotoUrl(MultipartFile multipartFile);

    /**
     * 给话题的点赞叠加
     * @param increment 叠加量
     * @param userId 话题id
     */
    void addStar(Long userId,int increment);

    /**
     * 根据某种排序方式查询用户
     * @param column 排序方式
     * @return 返回用户的集合
     */
    List<UserBasic> selectTopicBySortBy(String column);

    /**
     * 根据用户名判断用户是否存在
     * @param username 用户名
     * @return 返回
     */
    boolean isUserNameExits(String username);

    /**
     * 根据用户id查询用户基本信息
     * @param id 用户id
     * @return 返回用户基本信息对象
     */
    UserBasic searchUserBasicInfoById(Long id);

    /**
     * 查询用户隐私信息
     * @param id 用户id
     * @return 返回用户隐私信息对象
     */
    UserPrivacy searchUserPrivacyInfoById(Long id);

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 返回用户信息
     */
    UserBasic searchUserByUsername(String username);

    /**
     * 根据邮箱查询用户信息
     * @param email 邮箱
     * @return 返回用户信息
     */
    UserPrivacy searchUserByEmail(String email);



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
     */
    void updateUserBasicInfo(UserBasic userBasic);

    /**
     * 更新用户隐私信息
     * @param userPrivacy 用户隐私信息对象
     * @return 返回
     */
    int updateUserPrivacyInfo(UserPrivacy userPrivacy);

    /**
     * 获取用户的一些信息
     * @return 返回
     */
    ProfileVo getProfileVo();
}
