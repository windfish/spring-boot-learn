package com.demon.demo.mapper;

import com.demon.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * author: xuliang
 * since: 2020/4/27 15:36
 **/
public interface UserMapper {

    @Insert("insert into user(name, phone, age, create_time) values(#{name}, #{phone}, #{age}, #{createTime})")
    int insert(User user);

    @Select("select * from user")
    @Results({
            @Result(column = "create_time", property = "createTime")
    })
    List<User> getAll();

    @Select("select * from user where id=#{id}")
    User findById(int id);

}
