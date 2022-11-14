package com.example.disruptor.controller;

import com.example.disruptor.sinDisrupror.MessageEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private MessageEventProducer messageEventProducer;

    @GetMapping("/")
    public String test() {
        List<Map<String ,Object>> datas = new ArrayList<>();
        Map<String,Object> data = new HashMap<>();
        data.put("name","hj");
        datas.add(data);
        messageEventProducer.onData(datas);
        return "hello";
    }

}
