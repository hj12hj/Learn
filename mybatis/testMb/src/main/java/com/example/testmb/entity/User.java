package com.example.testmb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: hj
 * @date: 2022/10/8
 * @time: 2:01 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    Integer id;
    String name;
    String password;

    public User(int i) {
        this.id = i;
    }
}
