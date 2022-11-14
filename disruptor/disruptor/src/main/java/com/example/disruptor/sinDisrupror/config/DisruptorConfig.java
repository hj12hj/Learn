package com.example.disruptor.sinDisrupror.config;

import com.example.disruptor.sinDisrupror.MessageEvent;
import com.example.disruptor.sinDisrupror.MessageEventFactory;
import com.example.disruptor.sinDisrupror.MessageEventHandler;
import com.example.disruptor.sinDisrupror.MessageEventProducer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class DisruptorConfig {

    @Autowired
    MessageEventProducer messageEventProducer;


    @Autowired
    MessageEventHandler messageEventHandler;

    private Disruptor<MessageEvent> disruptor;


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


    @PreDestroy
    public void destroy() {
        disruptor.shutdown();
    }

}
