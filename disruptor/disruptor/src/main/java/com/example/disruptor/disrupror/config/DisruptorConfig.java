package com.example.disruptor.disrupror.config;

import com.example.disruptor.disrupror.MessageEvent;
import com.example.disruptor.disrupror.MessageEventFactory;
import com.example.disruptor.disrupror.MessageEventHandler;
import com.example.disruptor.disrupror.MessageEventProducer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class DisruptorConfig {

    @Autowired
    MessageEventProducer messageEventProducer;


    @Autowired
    MessageEventHandler messageEventHandler;

    Disruptor<MessageEvent> disruptor;


    @PostConstruct
    public void init() {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // 创建消息工厂类
        MessageEventFactory factory = new MessageEventFactory();

        // ringbuffer size，一般为2的指数倍
        int bufferSize = 1024;

        // 定义 Disruptor
        disruptor = new Disruptor<>(factory, bufferSize, threadFactory, ProducerType.SINGLE, new BlockingWaitStrategy());

        // 关联消费者
        disruptor.handleEventsWith(messageEventHandler);

        // 启动disruptor框架
        disruptor.start();

        // 获得ringbuffer用于消息发布
        RingBuffer<MessageEvent> ringBuffer = disruptor.getRingBuffer();

        messageEventProducer.setRingBuffer(ringBuffer);
    }



}
