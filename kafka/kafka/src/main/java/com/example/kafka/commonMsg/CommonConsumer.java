package com.example.kafka.commonMsg;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: hj
 * @date: 2022/10/19
 * @time: 9:27 AM
 */
@Component
public class CommonConsumer {
    @KafkaListener(topics = {"common"})
    public void listen(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }
}
