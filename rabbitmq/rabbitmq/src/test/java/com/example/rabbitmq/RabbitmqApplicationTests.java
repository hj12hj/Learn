package com.example.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final static MessagePostProcessor messagePostProcessor = message -> {

        message.getMessageProperties().setContentType("application/json");

        message.getMessageProperties().setContentEncoding("UTF-8");

        message.getMessageProperties().setHeader("token", "113333");

        return message;

    };

    @Test
    void contextLoads() {

        rabbitTemplate.convertAndSend("core", "core","111",messagePostProcessor);
    }

}
