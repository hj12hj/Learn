package com.example.disruptor.sinDisrupror;

import java.util.List;
import java.util.Map;

/**
 * @author: hj
 * @date: 2022/11/14
 * @time: 5:11 PM
 */
public class MessageEvent {
    private List<Map<String, Object>> data;

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }
}