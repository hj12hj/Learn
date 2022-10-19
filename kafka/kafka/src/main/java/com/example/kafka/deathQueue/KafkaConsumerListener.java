package com.example.kafka.deathQueue;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

import java.util.Optional;

/**
 * @author qfx
 */

@Component
public class KafkaConsumerListener {

    @Autowired
    private ConsumerFactory consumerFactory;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Bean
    public ConcurrentKafkaListenerContainerFactory containerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckMode(AckMode.MANUAL);
        // 最大重试次数3次
        factory.setErrorHandler(new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer(kafkaTemplate), new FixedBackOff(5 * 1000L, 1L)));
        return factory;
    }



    /**
     * 测试死信消息
     * @param acknowledgment 手动ack
     */
    @KafkaListener(topics = "kafka-topic2", containerFactory = "containerFactory", groupId = "testGroup")
    public void onMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) throws Exception {
        System.out.println("offset为 ："+record.offset());

        Optional kafkaMessage = Optional.ofNullable(record.value());

        if (!kafkaMessage.isPresent()) {
            throw new Exception("监听到的消息为空值");
        }


//        try {
//            /*业务逻辑*/
//            throw new RuntimeException("消息异常，进入死信队列...");
//        } catch (Exception e) {
//            acknowledgment.acknowledge();
//        }
    }

    @KafkaListener(id = "testGroup", topics = "kafka-topic2.DLT", groupId = "testGroup")
    public void dltListen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment,
                          @Header(KafkaHeaders.DLT_EXCEPTION_MESSAGE) String exception,
                          @Header(KafkaHeaders.DLT_EXCEPTION_STACKTRACE) String stacktrace) {
        System.out.println(exception);
        System.out.println(stacktrace);
        System.out.println("死信队列监听到的消息：" + record.value());
        acknowledgment.acknowledge();
    }


}

