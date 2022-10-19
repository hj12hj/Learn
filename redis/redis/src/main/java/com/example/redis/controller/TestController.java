package com.example.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hj
 * @date: 2022/10/19
 * @time: 1:40 PM
 */
@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    int i = 0;

    @GetMapping("/test")
    public String test() {
        redisTemplate.opsForList().leftPush("test1", "value" + i);
        redisTemplate.opsForList().leftPush("test2", "value" + i);
        i++;
        return "success";
    }
}
