package com.example.testmb.controller;

import com.example.testmb.entity.ReturnMsg;
import com.example.testmb.entity.User;
import com.example.testmb.mapper.UserMapper;
import com.example.testmb.utils.ReposeMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: hj
 * @date: 2022/10/12
 * @time: 2:17 下午
 */
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/getAll")
    public ReturnMsg hello() {
        PageHelper.startPage(1, 10);
        PageInfo<User> userPageInfo = new PageInfo<>(userMapper.findAll(new User()));

        return ReposeMsg.Success(userPageInfo);
    }


    @GetMapping("/insertAll")
    public ReturnMsg insertAll() {
        userMapper.insertAll(Arrays.asList(new User(6,"2","2"),new User(7,"2","2"),new User(8,"2","2")));
        return ReposeMsg.Success("插入成功");
    }


    @GetMapping("/findByConditional")
    public ReturnMsg findByConditional() {
        List<User> byConditional = userMapper.findByConditional("name", "54");
        return ReposeMsg.Success(byConditional);
    }

    @GetMapping("/findMaps")
    public ReturnMsg findMaps() {
        List<Map<String,Object>> findMaps = userMapper.findMaps();
        return ReposeMsg.Success(findMaps);
    }

}
