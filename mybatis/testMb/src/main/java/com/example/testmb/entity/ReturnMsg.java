package com.example.testmb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: hj
 * @date: 2022/10/12
 * @time: 2:22 下午
 */
@Data
@AllArgsConstructor
public class ReturnMsg {
    String code;
    Object data;
}
