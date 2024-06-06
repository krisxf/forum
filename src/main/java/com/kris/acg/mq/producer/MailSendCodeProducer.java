package com.kris.acg.mq.producer;

import com.kris.acg.common.MqConstant;
import com.kris.acg.entity.community.CommentMsg;
import com.kris.acg.entity.community.SendCode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Program: acg
 * @Description: 邮件发送激活码
 * @Author: kris
 * @Create: 2024-06-04 19:43
 **/

@Component
public class MailSendCodeProducer {
    @Resource
    RabbitTemplate rabbitTemplate;

    public void sendCode(SendCode sendCode) {
        rabbitTemplate.convertAndSend(MqConstant.MAIL_SEND_CODE_EXCHANGE,
                MqConstant.MAIL_SEND_CODE_ROUTING,
                sendCode);
    }
}
