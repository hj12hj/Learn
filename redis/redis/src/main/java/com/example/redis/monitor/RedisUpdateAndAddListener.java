package com.example.redis.monitor;

import lombok.Data;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.stereotype.Component;

/**
 * @author: hj
 * @date: 2022/10/19
 * @time: 4:12 PM
 */
@Component
@Data
public class RedisUpdateAndAddListener implements MessageListener {
    //监听的主题
    private  final PatternTopic topic = new PatternTopic("__keyevent@*__:set");

    @Override
    public void onMessage(Message message, byte[] pattern){
        String topic = new String(pattern);
        String msg = new String(message.getBody());
        System.out.println("收到key更新或修改，消息主题是："+ topic+",消息内容是："+msg);
    }

}

