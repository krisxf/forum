package com.kris.acg.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kris.acg.common.Constant;
import com.kris.acg.common.JwtConstant;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @Program: acg
 * @Description: JWT工具类
 * @Author: kris
 * @Create: 2023-08-13 15:35
 **/

public class JwtUtil {

    private static final String SECRET_KEY = "secret-key";


    public static String createToken(long userId, String username, String photoUrl) {

        return JWT.create()
                //发行时间
                .withIssuedAt(new Date())
                //有效截止时间
                .withExpiresAt(DateUtil.offsetDay(new Date(), Constant.TOKEN_EXPIRE_TIME))
                //载荷，存储不敏感的用户信息
                .withClaim(JwtConstant.KEY_USER_ID, userId)
                .withClaim(JwtConstant.KEY_USERNAME, username)
                .withClaim(JwtConstant.KEY_PHOTO_URL, photoUrl)
                //加密(摘要)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static void verifyToken(String token) throws JWTVerificationException {
        if (StrUtil.isBlank(token)) {
            throw new JWTVerificationException("token is null！");
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();

        verifier.verify(token);

    }

    public static Long getUserId(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim(JwtConstant.KEY_USER_ID).asLong();
    }

    public static String getUsername(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim(JwtConstant.KEY_USERNAME).asString();
    }

    public static String getPhotoUrl(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim(JwtConstant.KEY_PHOTO_URL).asString();
    }

    public static Date getExpire(String token) throws JWTDecodeException {
        return JWT.decode(token).getExpiresAt();
    }
}
