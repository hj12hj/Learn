package com.example.kafka.config;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author: hj
 * @date: 2022/10/19
 * @time: 10:08 AM
 */
@Component
public class ListenerErrorHandler {
    @Bean
    public ConsumerAwareListenerErrorHandler myConsumerAwareErrorHandler() {
        return new ConsumerAwareListenerErrorHandler() {

            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
                System.out.println("--- 发生消费异常 ---");
                System.out.println(message.getPayload());
                return null;
            }
        };
    }
}
