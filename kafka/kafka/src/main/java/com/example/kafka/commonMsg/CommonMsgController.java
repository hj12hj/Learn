package com.example.kafka.commonMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 简单消息的发送接受
 *
 * @author: hj
 * @date: 2022/10/19
 * @time: 9:26 AM
 */
@RestController
public class CommonMsgController {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;


    @GetMapping("/send")
    @Transactional
    public String send(String msg) {
        kafkaTemplate.send("common", msg);
        return "success";
    }

    @Autowired
    KafkaListenerEndpointRegistry registry;

    @GetMapping("/start")
    public String send2(String msg) {
        if (!registry.getListenerContainer("back").isRunning()) {
            registry.getListenerContainer("back").start();
        }
        return "success";
    }

}
