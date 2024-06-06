package com.kris.acg.service.Imp;

import com.kris.acg.common.Constant;
import com.kris.acg.common.KeyNames;
import com.kris.acg.service.TokenService;
import com.kris.acg.utils.JwtUtil;
import com.kris.acg.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-13 15:56
 **/

@Service
public class TokenServiceImpl implements TokenService {

    @Resource
    RedisUtil redisUtil;


    @Override
    public String createToken(Long userId, String username, String photoUrl) {
        //生成token
        String token;
        token = JwtUtil.createToken(userId, username, photoUrl);
        //记录token
        recordToken(token);
        return token;
    }

    @Override
    public void recordToken(String token) {
        if(hasRecord(token)){
            return;
        }
        String tokenKey = KeyNames.getTokenKey(token);
        redisUtil.set(tokenKey,token, Constant.TOKEN_EXPIRE_TIME);
    }

    @Override
    public void invalidToken(String token) {
        String tokenKey = KeyNames.getTokenKey(token);
        redisUtil.del(tokenKey);
    }

    @Override
    public boolean hasRecord(String token) {
        return redisUtil.get(KeyNames.getTokenKey(token)) != null;
    }
}
