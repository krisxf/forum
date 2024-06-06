package com.kris.acg.utils;

import com.kris.acg.common.Constant;
import com.kris.acg.common.KeyNames;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-10-05 14:45
 **/

@Component
public class IpUtil {
    @Resource
    RedisUtil  redisUtil;

    /**
     * ip 是否可以继续访问
     *
     * @param ip       访问的ip
     * @param maxValue 最大的访问次数
     * @return true 可以继续访问  false 超出限制，不可以继续访问
     */
    public boolean ipIsOk( String ip, int maxValue) {
        boolean ipIsOk = true;
        // 根据指定规则拼接生成此次访问的key
        String key = KeyNames.getIpMaxKey(ip);
        // 查询redis中已有的数据
        Integer count = (Integer) redisUtil.get(key);

        //如果是第一次访问
        if(count == null){
            //将其存入缓存
            addNewTime(key);
        }else{
            //判断其次数是否达到上限
            if(count < maxValue){
                //如果没有达到上限 将其次数加 1
                redisUtil.set(key,count+1);
                return ipIsOk;
            }else{
                ipIsOk = false;
            }
        }

        return ipIsOk;
    }

    /**
     * 往redis中添加新的数据，注：新增的值row在后
     *
     * @param key  key
     */
    public void addNewTime(String key) {
        redisUtil.incr(key,1);
        redisUtil.expire(key,Constant.IP_EXPIRE_TIME);
    }
}
