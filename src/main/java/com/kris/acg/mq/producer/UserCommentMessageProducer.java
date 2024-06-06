package com.kris.acg.mq.producer;

import com.kris.acg.common.MqConstant;
import com.kris.acg.entity.community.CommentMsg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-14 13:34
 **/

@Component
public class UserCommentMessageProducer {
    @Resource
    RabbitTemplate rabbitTemplate;

    public void sendCommentMessage(CommentMsg commentMsg) {
        rabbitTemplate.convertAndSend(MqConstant.USER_COMMENT_MESSAGE_EXCHANGE,
                MqConstant.USER_COMMENT_MESSAGE_ROUTING,
                commentMsg);
    }
}
