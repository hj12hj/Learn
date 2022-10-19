package com.example.redis;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisStringTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {

        SetOperations<String, Object> setOpt = redisTemplate.opsForSet();

//        setOpt.differenceAndStore("s2","s1","s3");

//        setOpt.intersectAndStore("s2","s1","s4");

        setOpt.unionAndStore("s2", "s1", "s5");


    }


    @Test
    void name() {

        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();

//        Set<Object> z1 = zSetOperations.reverseRange("z1", 0, 100);
//        System.out.println(z1);


        zSetOperations.incrementScore("z1", "z1", 1);
        System.out.println(zSetOperations.score("z1", "z1"));
    }


    @Test
    void name1() {

        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

        hashOperations.put("h1", "h1", "h1");
        Map<Object, Object> h1 = hashOperations.entries("h1");

        System.out.println(h1);
    }


    @Test
    void name222() {
        StreamOperations<String, Object, Object> streamOperations = redisTemplate.opsForStream();
//        Map<String,String> map = new HashMap<>();
//        map.put("name","hj");
//        streamOperations.add("s100",map);

    }
}
