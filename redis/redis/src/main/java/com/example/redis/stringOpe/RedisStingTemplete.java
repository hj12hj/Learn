package com.example.redis.stringOpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: hj
 * @date: 2022/10/19
 * @time: 11:47 AM
 */
@Component
public class RedisStingTemplete {

    @Autowired
    private RedisTemplate<String ,Object> redisTemplate;



}
