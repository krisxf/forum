package com.kris.acg;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-12 14:29
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

    @Resource
    AmqpAdmin amqpAdmin;

    @Resource
    RabbitTemplate rabbitTemplate;



    @Test
    public void sendMessageTest(){
        String msg = "Hello World";
        rabbitTemplate.convertAndSend("hello-java-exchange","hello.java",msg);
        log.info("消息已经发送{}",msg);
    }

    @Test
    public void createExchange() {
        DirectExchange directExchange = new DirectExchange("hello-java-exchange",true,false);
        amqpAdmin.declareExchange(directExchange);
        log.info("Exchange({}) success!","hello-java-exchange");
    }

    @Test
    public void createQueue(){
        Queue queue = new Queue("hello-java-queue",true,false,false);
        amqpAdmin.declareQueue(queue);
        log.info("Exchange({}) success!","hello-java-queue");
    }

    @Test
    public void createBinding(){
        // String destination, 目的地
        // Binding.DestinationType, 目的地类型
        // String exchange, 交换机
        // String routingKey, 路由键
        // @Nullable Map<String, Object> arguments 自定义参数
        // 将exchange指定的交换机和目的地进行绑定，使用routingKey作为指定的路由键
        Binding binding = new Binding("hello-java-queue",
                Binding.DestinationType.QUEUE,
                "hello-java-exchange",
                "hello.java",
                null);
        amqpAdmin.declareBinding(binding);
        log.info("Exchange({}) success!","hello-java-binding");
    }


}
