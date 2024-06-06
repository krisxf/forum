package com.kris.acg.service.Imp;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.kris.acg.common.Constant;
import com.kris.acg.common.HeaderKeyConstant;
import com.kris.acg.common.KeyNames;
import com.kris.acg.entity.community.SendCode;
import com.kris.acg.entity.req.UserLogin;
import com.kris.acg.entity.req.UserRegister;
import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.entity.user.UserPrivacy;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.mapper.UserMapper;
import com.kris.acg.mq.producer.MailSendCodeProducer;
import com.kris.acg.service.LoginService;
import com.kris.acg.service.TokenService;
import com.kris.acg.utils.JwtUtil;
import com.kris.acg.utils.MailUtil;
import com.kris.acg.utils.RedisUtil;
import com.kris.acg.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-10 18:42
 **/

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    RedisUtil redisUtil;
    @Resource
    UserMapper userMapper;
    @Resource
    TokenService tokenService;
    @Resource
    MailSendCodeProducer sendCode;

    @Override
    public String login(UserLogin userLogin) {
        //查找用户对象
        UserPrivacy userPrivacy = userMapper.selectUserByEmail(userLogin.getEmail());
        //将密码加密后与请求的密码进行比较
        String salt = userPrivacy.getSalt();
        String pwd = DigestUtil.md5Hex(salt + userLogin.getPassword());
        if(pwd == null){
            throw  new BusinessException("用户密码为空");
        }
        //失败抛出异常
        if(!pwd.equals(userPrivacy.getPwd())){
            throw new BusinessException("密码错误！");
        }
        // 如果成功返回token
        //从redis中获取
        String userInfoKey = KeyNames.getUserInfoKey(userPrivacy.getUserId());
        String json = (String) redisUtil.get(userInfoKey);
        UserBasic userBasic = null;
        if(json != null){
            userBasic = JSON.parseObject(json,UserBasic.class);
        }else{
            userBasic = userMapper.selectUserBasicInfoById(userPrivacy.getUserId());
            //将用户对象转化为json字符串，然后存入redis中
            String userInfo = JSON.toJSONString(userBasic);
            redisUtil.set(userInfoKey,userInfo,Constant.USERINFO_EXPIRE_TIME);
        }
        return tokenService.createToken(userBasic.getId(),userBasic.getUsername(),userBasic.getPhotoUrl());
    }

    @Override
    public String register(UserRegister userRegister) {
        //创建用户基本信息对象
        UserBasic userBasic = new UserBasic();
        userBasic.setUsername(userRegister.getUsername());
        userBasic.setPhotoUrl("");
        //使用雪花算法生成用户id
        long userId = IdUtil.getSnowflakeNextId();
        userBasic.setId(userId);
        log.debug("雪花算法生成的用户id为 ：" + userId);
        //创建用户隐私信息对象
        UserPrivacy userPrivacy = new UserPrivacy();
        userPrivacy.setUserId(userId);
        userPrivacy.setRegisterTime(new Date());
        userPrivacy.setEmail(userRegister.getEmail());
        //给用户随机一个盐
        String salt = IdUtil.simpleUUID();
        userPrivacy.setSalt(salt);
        log.debug("生成的随机盐为 ： " + salt);
        //给用户的密码进行盐加密
        String pwd = userRegister.getPassword();
        pwd = DigestUtil.md5Hex(salt + pwd);
        userPrivacy.setPwd(pwd);
        log.debug("加密后的密码为 ： " + pwd);
        //将用户对象转化为json字符串，然后存入redis中
        String json = JSON.toJSONString(userBasic);
        String userInfoKey = KeyNames.getUserInfoKey(userId);
        redisUtil.set(userInfoKey,json,Constant.USERINFO_EXPIRE_TIME);
        //将用户的信息插入表中
        userMapper.insertUserBasicInfo(userBasic);
        userMapper.insertUserPrivacyInfo(userPrivacy);
        // 返回token
        return tokenService.createToken(userBasic.getId(),userBasic.getUsername(),userBasic.getPhotoUrl());
    }

    @Override
    public void logout(HttpServletRequest request) {
        //获取cookie中的token
        String token = request.getHeader(HeaderKeyConstant.AUTHORIZATION);
        //将redis中的信息删除
        Long userId = JwtUtil.getUserId(token);
        String userInfoKey = KeyNames.getUserInfoKey(userId);
        redisUtil.del(userInfoKey);
        tokenService.invalidToken(token);
    }

    @Override
    public void checkEmailCode(String email,String verifyCode) {
        //获取激活码
        String key = KeyNames.getEmailVerifyCodeKey(email);
        String code = (String) redisUtil.get(key);
        //判断是否一致
        if(code == null){
            throw new BusinessException("激活码未发送");
        }
        if(!code.equals(verifyCode)){
            //不一致
            throw new BusinessException("激活码错误");
        }
        //一致返回true 并将其在redis中删除
        redisUtil.del(key);
    }

    @Override
    public void sendEmailVerifyCode(String email) {
        //获得激活码
        String code = RandomUtil.randomString(Constant.VERIFY_CODE_LENGTH);
        log.info("激活码为：{}",code);
        //将激活码缓存至Redis中
        redisUtil.set(KeyNames.getEmailVerifyCodeKey(email),code,Constant.EXPIRE_TIME);
        //发送邮件
//        mailUtil.sendMail(email,"激活码",code);
        sendCode.sendCode(new SendCode(email,"激活码",code));
    }

    @Override
    public void writeCodeImageToResponseAndRecord(HttpServletRequest request, HttpServletResponse response) {
        int width = 100;
        int height = 40;
        int codeCount = 4;
        int thickness = 4;
        /**
         * 注意验证码需要系统有默认字体，如果使用docker alpine，会因为没有默认字体而出错
         */
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(width, height, codeCount, thickness);
        //图形验证码写出，可以写出到文件，也可以写出到流
        ServletOutputStream outputStream=null;
        try {
            outputStream = response.getOutputStream();
            captcha.write(outputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        //获取验证码中的文字内容
        String verifyCode = captcha.getCode();
        //记录到redis
        String sessionId = request.getSession().getId();

        redisUtil.set(KeyNames.getImageVerifyCodeKey(sessionId),verifyCode,Constant.EXPIRE_TIME);

    }

    @Override
    public void checkImageCode(HttpServletRequest request,String verifyCode) {
        //获取激活码
        String key = KeyNames.getImageVerifyCodeKey(request.getSession().getId());
        String code = (String) redisUtil.get(key);
        //判断是否一致
        if(code == null){
            throw new BusinessException("激活码已过期");
        }
        if(!code.equals(verifyCode)){
            //不一致返回false
            throw new BusinessException("激活码不正确");
        }
        //一致返回true 并将其在redis中删除
        redisUtil.del(key);
    }
}
