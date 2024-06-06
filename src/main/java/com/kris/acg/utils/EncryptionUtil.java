package com.kris.acg.utils;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * @Program: acg
 * @Description: 加密工具类
 * @Author: kris
 * @Create: 2023-08-13 23:55
 **/

public class EncryptionUtil {

    public static String encryption(String str,String salt){
        return DigestUtil.md5Hex(salt + str);
    }
}
