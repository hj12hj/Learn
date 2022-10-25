package com.example.rabbitmq.fantout;

import com.example.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hj
 * @date: 2022/10/21
 * @time: 2:49 PM
 */
@RestController
public class FanoutExchangeProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void sendFanoutExchangeMessage() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("fanout_exchange","",new User(i+"",i+"发布订阅车车"));
        }
    }


}
