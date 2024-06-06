package com.kris.acg;

import com.kris.acg.utils.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: acg
 * @Description: 邮件测试
 * @Author: kris
 * @Create: 2023-08-10 20:21
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailUtilTest {

    @Autowired
    MailUtil mailUtil;

    @Test
    public void mailTest(){
        mailUtil.sendMail("2668380064@qq.com","激活码","12345");
    }
}
