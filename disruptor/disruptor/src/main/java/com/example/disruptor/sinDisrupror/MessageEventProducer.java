package com.example.disruptor.sinDisrupror;

import com.lmax.disruptor.RingBuffer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author: hj
 * @date: 2022/11/14
 * @time: 5:12 PM
 */
@Component
public class MessageEventProducer {

    private  RingBuffer<MessageEvent> ringBuffer;


    public void setRingBuffer(RingBuffer<MessageEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(List<Map<String, Object>> datas) {
        long sequence = ringBuffer.next();
        try {
            MessageEvent event = ringBuffer.get(sequence);
            event.setData(datas);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}