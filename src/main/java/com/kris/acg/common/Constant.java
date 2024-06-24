package com.kris.acg.common;

/**
 * @Program: acg
 * @Description: 常量名
 * @Author: kris
 * @Create: 2023-08-10 20:18
 **/

public class Constant {

    /**
     * 默认游客id
     */
    public static final int DEFAULT_ID = 999;

    /**
     * 激活码长度
     */
    public static final int VERIFY_CODE_LENGTH = 8;
    /**
     * 激活码过期时间 3分钟
     */
    public static final int EXPIRE_TIME = 3 * 60;

    /**
     * 哈希表的大小
     */
    public static final int HASH_MAP_SIZE = 6;
    /**
     * TOKEN过期时间 1天
     */
    public static final int TOKEN_EXPIRE_TIME = 24 * 60 * 60;
    /**
     * TOKEN过期时间  1天
     */
    public static final int USERINFO_EXPIRE_TIME = 24 * 60 * 60;
    /**
     * ip过期时间
     */
    public static final int IP_EXPIRE_TIME = 60;
    /**
     * 默认的过期时间    30分钟
     */
    public static final int DEFAULT_EXPIRE_TIME = 30 * 60;
}
