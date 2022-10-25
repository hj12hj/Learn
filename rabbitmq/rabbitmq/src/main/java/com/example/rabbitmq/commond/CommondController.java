package com.example.rabbitmq.commond;

import com.example.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hj
 * @date: 2022/10/21
 * @time: 2:22 PM
 */
//@RestController
public class CommondController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send() {

        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("test", new User("hj", i+""));
        }

//        rabbitTemplate.convertAndSend("test", new User("hj", "18"));
        return "success";
    }

}
