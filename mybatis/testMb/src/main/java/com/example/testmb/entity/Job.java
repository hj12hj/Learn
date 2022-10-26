package com.example.testmb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: hj
 * @date: 2022/10/18
 * @time: 2:27 下午
 */
@Data
@AllArgsConstructor
public class Job {
    Integer id;
    String groupName;
    String name;
    String cronExpression;
    String status;
}
