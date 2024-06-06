package com.kris.acg.service.Imp;

import com.alibaba.fastjson.JSON;
import com.kris.acg.common.KeyNames;
import com.kris.acg.common.RedisConstant;
import com.kris.acg.entity.community.Star;
import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.mapper.StarMapper;
import com.kris.acg.service.StarService;
import com.kris.acg.service.TopicService;
import com.kris.acg.service.UserService;
import com.kris.acg.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-05 14:43
 **/

@Service
@Slf4j
public class StarServiceImpl implements StarService {

    @Resource
    StarMapper starMapper;
    @Resource
    TopicService topicService;
    @Resource
    UserService userService;
    @Resource
    RedisUtil redisUtil;

    @Override
    public boolean isUserStar(Long userId, Long topicId) {
//        String starKey = KeyNames.getStarKey(topicId);
//        redisUtil.sHasKey(starKey, userId);
        Star star = starMapper.selectUserIsStar(userId, topicId);
        return star != null;
    }

    @Override
    public List<Star> queryTopicStar(Long topicId) {
        return starMapper.selectTopicStar(topicId);
    }

    @Override
    public List<Star> queryUserStar(Long userId) {
        List<Star> stars = starMapper.selectUserStar(userId);
        for (Star star: stars
             ) {
            star.setTopic(topicService.queryTopicById(star.getTopicId()));
        }
        return stars;
    }

    @Override
    public void addStar(Long userId,Long topicId) {
        //判断是否已经点过赞了
        boolean userStar = isUserStar(userId, topicId);
        if(userStar){
            throw new BusinessException("已经点过赞了");
        }
        //创建Star对象
        Star star = new Star();
        star.setCreateTime(new Date());
        star.setTopicId(topicId);
        star.setUserId(userId);

        //增加点赞
        int insertStar = starMapper.insertStar(star);
        //redis中设置点赞
        String starKey = KeyNames.getStarKey(topicId);
        redisUtil.sSet(starKey,userId);
        //给topic的点赞数加一
        topicService.addStar(topicId,insertStar);
        //更新排行榜  直接删除
        UserBasic userBasic = userService.searchUserBasicInfoById(userId);
        String str = JSON.toJSONString(userBasic);
        String userRankColumnKey = KeyNames.getUserRankColumnKey(RedisConstant.USER_RANK_STAR_COUNT);
        redisUtil.zSSRem(userRankColumnKey,str);

        //给topic的作者点赞数加一
        userService.addStar(userId,insertStar);

    }

    @Override
    public void deleteTopicStar(Long topicId) {
        starMapper.deleteTopicStar(topicId);
    }

    @Override
    public void deleteUserStar(Long userId, Long topicId) {
        //判断是否已经点过赞了
        boolean userStar = isUserStar(userId, topicId);
        if(!userStar){
            throw new BusinessException("已经取消过赞了");
        }
        int deleteUserStar = starMapper.deleteUserStar(userId, topicId);
        int increment = -deleteUserStar;
        //更新话题的点赞数
        topicService.addStar(topicId,increment);
        //更新排行榜  删除在添加
        UserBasic userBasic = userService.searchUserBasicInfoById(userId);
        String str = JSON.toJSONString(userBasic);
        String userRankColumnKey = KeyNames.getUserRankColumnKey(RedisConstant.USER_RANK_STAR_COUNT);
        redisUtil.zSSRem(userRankColumnKey,str);
        userBasic.setStarCount(userBasic.getStarCount() - 1);
        str = JSON.toJSONString(userBasic);
        redisUtil.zSSet(userRankColumnKey,str,userBasic.getStarCount());
        //更新作者的点赞数
        userService.addStar(userId,increment);
        //删除redis中的缓存
        String starKey = KeyNames.getStarKey(topicId);
        redisUtil.setRemove(starKey,userId);
    }
}
