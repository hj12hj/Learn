package com.example.testmb.utils;

import com.example.testmb.entity.ReturnMsg;

/**
 * @author: hj
 * @date: 2022/10/12
 * @time: 2:23 下午
 */
public class ReposeMsg {
    public static ReturnMsg Success(Object object) {
        return new ReturnMsg("200",object);
    }
}
