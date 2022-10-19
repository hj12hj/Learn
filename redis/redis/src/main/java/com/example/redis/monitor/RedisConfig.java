package com.example.redis.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author: hj
 * @date: 2022/10/19
 * @time: 4:11 PM
 */
@Configuration("config")
public class RedisConfig {


    /*  监听需要开启redis 监听设置  */

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUpdateAndAddListener redisUpdateAndAddListener;

    @Autowired
    private RedisDeleteListener redisDeleteListener;

    @Autowired
    private RedisExpiredListener redisExpiredListener;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //监听所有的key的set事件
        container.addMessageListener(redisUpdateAndAddListener, redisUpdateAndAddListener.getTopic());
        //监听所有key的删除事件
        container.addMessageListener(redisDeleteListener, redisDeleteListener.getTopic());
        //监听所有key的过期事件
        container.addMessageListener(redisExpiredListener, redisExpiredListener.getTopic());
        return container;
    }


}
