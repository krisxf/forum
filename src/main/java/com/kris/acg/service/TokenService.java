package com.kris.acg.service;

/**
 * @author Kristin
 */
public interface TokenService {
    /**
     * 根据用户信息创建token
     * @param userId 用户id
     * @param username 用户名
     * @param photoUrl 头像路径
     * @return 返回token
     */
    String createToken(Long userId,String username,String photoUrl);

    /**
     * 在redis中记录token
     * @param token token
     */
    void recordToken(String token);

    /**
     * 删除token
     * @param token token
     */
    void invalidToken(String token);

    /**
     * 是否token已经被记录
     * @param token token
     * @return 返回
     */
    boolean hasRecord(String token);

}
