package com.example.testmb;

import com.example.testmb.entity.User;
import com.example.testmb.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestMbApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        PageHelper.startPage(1, 10);
        userMapper.findByBind(new User(1,"1","1")).forEach(System.out::println);
    }

    @Test
    void name() {
        userMapper.updateByConditions1(new User(1,"1","1"));
    }
}
