package com.kris.acg.mq.consumer;

import com.kris.acg.common.MqConstant;
import com.kris.acg.entity.community.CommentMsg;
import com.kris.acg.service.CommentMsgService;
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
 * @Create: 2023-09-14 13:36
 **/

@Component
@Slf4j
@RabbitListener(queues = MqConstant.USER_COMMENT_MESSAGE_QUEUE)
public class UserCommentMessageConsumer {

    @Resource
    CommentMsgService commentMsgService;

    @RabbitHandler
    public void handleCommentMsg(Channel channel, Message message, CommentMsg commentMsg) {
        log.info("发送信息：{}", commentMsg);
        try {
            //往数据库中插入评论的信息
            commentMsgService.addCommentMsg(commentMsg);


        } catch (Exception e) {
            log.error("发送信息失败 error:{} 对象：{}", e.getMessage(), commentMsg);

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
