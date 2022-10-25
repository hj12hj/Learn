package com.example.rabbitmq.commond;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: hj
 * @date: 2022/10/21
 * @time: 2:23 PM
 */
//@Configuration
public class CommondConfig {
    @Bean
    public Queue test() {
        return new Queue("test");
    }

}
