package com.example.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: hj
 * @date: 2022/10/20
 * @time: 1:34 PM
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    String name;
    String age;
}
