package com.kris.acg.common;

/**
 * @Program: acg
 * @Description: 存入redis中的键值名称
 * @Author: kris
 * @Create: 2023-08-10 18:52
 **/

public class KeyNames {
    public static final String EMAIL_VERIFY_CODE = "acg:email:verify:code";
    public static final String IMAGE_VERIFY_CODE = "acg:image:verify:code";
    public static final String TOKEN = "acg:token";
    public static final String USER_INFO_PREFIX = "acg:user:info:id";
    public static final String TOPIC_PAGE_VIEW = "acg:topic:pageView";
    public static final String TOPIC_STAR = "acg:topic:star";
    public static final String USER_RANK_COLUMN = "acg:user:rank";
    public static final String IP_MAX = "ip:max";

    public static String getUserInfoKey(Long id) {
        return USER_INFO_PREFIX + id;
    }

    public static String getEmailVerifyCodeKey(String email) {
        return EMAIL_VERIFY_CODE + email;
    }

    public static String getImageVerifyCodeKey(String sessionId) {
        return IMAGE_VERIFY_CODE + sessionId;
    }

    public static String getTokenKey(String token){
        return TOKEN + token;
    }

    public static String getPageViewKey(Long  topicId){
        return TOPIC_PAGE_VIEW + topicId;
    }

    public static String getStarKey(Long topicId){
        return TOPIC_STAR + topicId;
    }

    public static String getUserRankColumnKey(String column){ return USER_RANK_COLUMN + column;}

    public static String getIpMaxKey(String ip){return IP_MAX + ip;}
}

