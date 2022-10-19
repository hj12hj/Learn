package com.example.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author: hj
 * @date: 2022/10/19
 * @time: 1:16 PM
 */
@Component
public class Init {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private volatile Boolean isRun = true;

    @PostConstruct
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isRun) {
                        Object test = redisTemplate.opsForList().rightPop("test*", 100, TimeUnit.MICROSECONDS);
                        if (test != null) {
                            System.out.println(test);
                        }
                    }
                }
            }
        }).start();
    }

    @PreDestroy
    public void destroy() {
        isRun = false;
    }

}
