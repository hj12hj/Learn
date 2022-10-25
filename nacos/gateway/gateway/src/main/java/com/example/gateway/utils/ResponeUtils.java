package com.example.gateway.utils;


import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author: hj
 * @date: 2022/10/25
 * @time: 10:58 AM
 */
@Component
public class ResponeUtils {


    public DataBuffer getResponseBuffer(ServerHttpResponse response,Object data) {


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", -1);
        jsonObject.put("data", data);
        //把json对象转换成字节数组
        byte[] bits = jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8);
        //把字节数据转换成一个DataBuffer
        DataBuffer buffer = response.bufferFactory().wrap(bits);

        return buffer;


    }
}