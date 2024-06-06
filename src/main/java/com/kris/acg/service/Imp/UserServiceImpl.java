package com.kris.acg.service.Imp;

import com.alibaba.fastjson.JSON;
import com.kris.acg.common.*;
import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.entity.user.UserPrivacy;
import com.kris.acg.entity.vo.ProfileVo;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.mapper.UserMapper;
import com.kris.acg.service.TokenService;
import com.kris.acg.service.UserService;
import com.kris.acg.utils.ImageUtil;
import com.kris.acg.utils.MinioUtil;
import com.kris.acg.utils.RedisUtil;
import com.kris.acg.utils.UserContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.*;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-04 16:32
 **/

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    MinioUtil minioUtil;
    @Resource
    RedisUtil redisUtil;
    @Resource
    TokenService tokenService;

    @Override
    public List<UserBasic> searchAllUserBasicMsg() {
        return userMapper.selectAllUserBasicMsg();
    }

    @Override
    public void addTopic(Long userId, int increment) {
        userMapper.updateTopic(userId,increment);
    }

    @Override
    public String updatePhotoUrl(MultipartFile multipartFile) {
        //压缩图片
        InputStream inputStream = ImageUtil.compressAsFixedWebpImage(multipartFile,
                CoverImageConstants.WIDTH,
                CoverImageConstants.HEIGHT,
                CoverImageConstants.LIMIT_COMPRESS_SIZE);
        //生成图片的url
        Long userId = UserContext.getUserId();
        UserBasic user = userMapper.selectUserBasicInfoById(userId);
        if(user == null){
            throw new BusinessException("用户未登陆");
        }
        Date date = new Date();
        String suffix = String.format("%s.%s", userId, FileFormatConstants.JPG);
        String url = minioUtil.generateBaseUrl(StorePrefixConstants.USER_PHOTO, date, suffix);
        //上传
        String photoUrl = null;
        photoUrl = minioUtil.upload(inputStream,url,FileFormatConstants.JPG_CONTENT_TYPE);
        //修改用户的头像url
        user.setPhotoUrl(photoUrl);
        userMapper.updateUserBasicInfo(user);
        //从redis中删除user信息
        String userInfoKey = KeyNames.getUserInfoKey(userId);
        redisUtil.del(userInfoKey);
        //返回token
        return tokenService.createToken(userId, user.getUsername(), photoUrl);
    }

    @Override
    public void addStar(Long userId, int increment) {
        userMapper.updateStar(userId,increment);
    }

    @Override
    public List<UserBasic> selectTopicBySortBy(String column) {
        //从redis中取
        String userRankColumnKey = KeyNames.getUserRankColumnKey(column);
        Set<ZSetOperations.TypedTuple<Object>> objects = redisUtil.zSSGet(userRankColumnKey);
        List<UserBasic> list = new ArrayList<>();
        //如果redis中有数据 直接获取并进行强转 加入链表
        if((objects != null) && (objects.size() != 0)){
            for (ZSetOperations.TypedTuple<Object>  o: objects
                 ) {
                String str = (String) o.getValue();
                UserBasic userBasic = JSON.parseObject(str,UserBasic.class);
                list.add(userBasic);
            }
            Collections.reverse(list);
        }else{
            //如果redis中没有数据 从数据库中查询
            List<UserBasic> userBasics = userMapper.selectTopicBySortBy(column);
            //将这些对象放入redis中
            for (UserBasic user: userBasics
                 ) {
                //先将对象转成json字符串
                String s = JSON.toJSONString(user);
                if(RedisConstant.USER_RANK_STAR_COUNT.equals(column)){
                    redisUtil.zSSetAndTime(userRankColumnKey,12 * 60 * 60,s,user.getStarCount());
                }else{
                    redisUtil.zSSetAndTime(userRankColumnKey,12 * 60 * 60,s,user.getTopicCount());
                }
                list.add(user);
            }
        }
        return list;
    }

    @Override
    public boolean isUserNameExits(String username) {
        UserBasic userBasic = userMapper.selectUserByUsername(username);
        return userBasic == null;
    }

    @Override
    public UserBasic searchUserBasicInfoById(Long id) {
        return userMapper.selectUserBasicInfoById(id);
    }

    @Override
    public UserPrivacy searchUserPrivacyInfoById(Long id) {
        return userMapper.selectUserPrivacyInfoById(id);
    }

    @Override
    public UserBasic searchUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public UserPrivacy searchUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    @Override
    public int deleteUserBasicInfoById(Long id) {
        return userMapper.deleteUserBasicInfoById(id);
    }

    @Override
    public int deleteUserPrivacyInfoById(Long id) {
        return userMapper.deleteUserPrivacyInfoById(id);
    }

    @Override
    public void updateUserBasicInfo(UserBasic userBasic) {
        String userInfoKey = KeyNames.getUserInfoKey(userBasic.getId());
        redisUtil.del(userInfoKey);
        userMapper.updateUserBasicInfo(userBasic);
    }

    @Override
    public int updateUserPrivacyInfo(UserPrivacy userPrivacy) {
        return userMapper.updateUserPrivacyInfo(userPrivacy);
    }

    @Override
    public ProfileVo getProfileVo() {
        Long id = UserContext.getUserId();
        if(id == null){
            throw new BusinessException("用户未登陆");
        }
        //先查询用户的基本信息
        UserBasic userBasic = null;
        String userInfoKey = KeyNames.getUserInfoKey(id);
        String userInfo = (String) redisUtil.get(userInfoKey);
        if(userInfo != null){
            userBasic = JSON.parseObject(userInfo,UserBasic.class);
        }else{
            userBasic = userMapper.selectUserBasicInfoById(id);
        }
        //再查询用户的隐私信息
        UserPrivacy userPrivacy = userMapper.selectUserPrivacyInfoById(id);
        //将需要的信息封装到对象返回
        ProfileVo profileVo = new ProfileVo();
        profileVo.setEmail(userPrivacy.getEmail());
        profileVo.setRegisterTime(userPrivacy.getRegisterTime());
        profileVo.setUsername(userBasic.getUsername());
        profileVo.setStarCount(userBasic.getStarCount());
        profileVo.setTopicCount(userBasic.getTopicCount());

        return profileVo;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserPrivacy userPrivacy = userMapper.selectUserByEmail(email);
//        if (userPrivacy!=null){
//            // 组装参数
//            User user = new User();
//            BeanUtils.copyProperties(userPrivacy,user);
//            return user;
//        }
//        return null;
//    }
}
