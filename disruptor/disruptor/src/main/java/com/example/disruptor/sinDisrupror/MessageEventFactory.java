package com.example.disruptor.sinDisrupror;

import com.lmax.disruptor.EventFactory;

/**
 * @author: hj
 * @date: 2022/11/14
 * @time: 5:12 PM
 */
public class MessageEventFactory implements EventFactory<MessageEvent> {
    @Override
    public MessageEvent newInstance() {
        return new MessageEvent();
    }
}