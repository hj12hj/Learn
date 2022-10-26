package com.example.testmb.mapper;

import com.example.testmb.entity.Job;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: hj
 * @date: 2022/10/18
 * @time: 2:33 下午
 */
@Mapper
public interface JobMapper {
    List<Job> findAll();
}
