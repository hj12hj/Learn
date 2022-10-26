package com.example.testmb.mapper;

import com.example.testmb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * @author: hj
 * @date: 2022/10/8
 * @time: 2:01 下午
 */
@Mapper
public interface UserMapper {
    List<User> findAll(User user);


    void insertAll(List<User> user);

    List<User> findByConditional(@Param("column") String column, @Param("value") String value);


    List<Map<String, Object>> findMaps();


    List<User> findByConditions(User user);


    void updateByConditions(User user);


    @Update({"<script>",
            "update user",
            "<set>",
            "<if test='name != null'>",
            "name = #{name},",
            "</if>",
            "<if test='password != null'>",
            "password = #{password},",
            "</if>",
            "</set>",
            "where id = #{id}",
            "</script>"})
    void updateByConditions1(User user);


    List<User> findByBind(User user);

}
