package com.kris.acg.mq.config;

import com.kris.acg.common.MqConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-14 13:21
 **/

@Configuration
public class UserCommentMessageConfig {

    @Resource
    AmqpAdmin amqpAdmin;

    /**
     * 创建队列
     * @return 返回一个队列
     */
    @Bean
    public Queue syncEsQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(MqConstant.USER_COMMENT_MESSAGE_QUEUE, true,false,false);
    }

    /**
     * 创建交换机
     * @return 返回一个直接交换机
     */
    @Bean
    DirectExchange syncEsExchange() {
        return new DirectExchange(MqConstant.USER_COMMENT_MESSAGE_EXCHANGE, true, false);
    }

    /**
     *  绑定  将队列和交换机绑定, 并设置用于匹配键
     */

    @Bean
    Binding bindingSyncEs() {
        return new Binding(MqConstant.USER_COMMENT_MESSAGE_QUEUE,
                Binding.DestinationType.QUEUE,
                MqConstant.USER_COMMENT_MESSAGE_EXCHANGE,
                MqConstant.USER_COMMENT_MESSAGE_ROUTING,
                null);
    }
}
