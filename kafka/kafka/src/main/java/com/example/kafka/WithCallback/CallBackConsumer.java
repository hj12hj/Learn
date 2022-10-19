package com.example.kafka.WithCallback;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author: hj
 * @date: 2022/10/19
 * @time: 9:27 AM
 */
@Component
public class CallBackConsumer {
    //        @KafkaListener(topicPartitions = {
//            @TopicPartition(topic = "back", partitionOffsets = {
//                    @PartitionOffset(partition = "0", initialOffset = "0")
//            }),
//    })
    @KafkaListener(id = "back",topics = {"back"},containerFactory = "delayContainerFactory")
    public void listen(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("back消费：" + record.topic() + "-" + record.partition() + "-" + record.value() + "-" + record.offset());
//        int a = 1 / 0;
        ack.acknowledge();
    }
}
