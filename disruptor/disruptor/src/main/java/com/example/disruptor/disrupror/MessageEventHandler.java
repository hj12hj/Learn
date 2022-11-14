package com.example.disruptor.disrupror;

import com.lmax.disruptor.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author: hj
 * @date: 2022/11/14
 * @time: 5:13 PM
 */
@Component
public class MessageEventHandler implements EventHandler<MessageEvent>
{
    public void onEvent(MessageEvent event, long sequence, boolean endOfBatch)
    {
        System.out.println("Event: " + event.getData());
    }
}