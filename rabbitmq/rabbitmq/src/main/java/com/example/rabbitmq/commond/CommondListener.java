package com.example.rabbitmq.commond;

import com.example.rabbitmq.entity.User;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author: hj
 * @date: 2022/10/20
 * @time: 1:32 PM
 */
//@Component
public class CommondListener {

    @RabbitHandler
    @RabbitListener(queues = "test")
    public void receive1(User  message) {
        System.out.println("1接收到消息：" + message);
    }


    @RabbitHandler
    @RabbitListener(queues = "test")
    public void receive2(User  message) {
        System.out.println("2接收到消息：" + message);
    }
}
