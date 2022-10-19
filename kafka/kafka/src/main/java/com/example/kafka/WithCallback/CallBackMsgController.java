package com.example.kafka.WithCallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFutureCallback;
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
public class CallBackMsgController {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;


    @GetMapping("/backSend")
    @Transactional
    public String send(String msg) {
        kafkaTemplate.send("back", msg).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送失败");
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println(result);
            }
        });
        return "success";
    }


}
