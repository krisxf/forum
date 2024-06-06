package com.kris.acg.mq.consumer;

import com.kris.acg.common.MqConstant;
import com.kris.acg.entity.community.SendCode;
import com.kris.acg.utils.MailUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-04 19:45
 **/
@Component
@Slf4j
@RabbitListener(queues = MqConstant.MAIL_SEND_CODE_QUEUE)
public class MailSendCodeConsumer {
    @Resource
    MailUtil mailUtil;

    @RabbitHandler
    public void handleSendCode(Channel channel, Message message, SendCode sendCode) {
        log.info("发送激活码：{}", sendCode.getCode());
        try {
            //发送激活码
            mailUtil.sendMail(sendCode.getEmail(), sendCode.getMsg(), sendCode.getCode());


        } catch (Exception e) {
            log.error("发送信息失败 error:{} 对象：{}", e.getMessage(), sendCode);

        } finally {
            String messageBody = new String(message.getBody());
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                log.error("发送信息失败 error:{} message:{}", e.getMessage(), messageBody);
                try {
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                } catch (IOException ex) {
                    log.error("发送信息失败 error:{} message:{}", ex.getMessage(), messageBody);
                }
            }
        }

    }
}
