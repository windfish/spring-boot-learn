package com.demon.spring_boot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demon.spring_boot.entity.UserMybatis;

@Mapper
public interface UserMapper {

    @Select("select * from user where name = #{name}")
    UserMybatis findByName(@Param("name")String name);
    
    @Insert("insert into user(id,name,age) values(#{id}, #{name}, #{age})")
    int insert(UserMybatis user);
    
}
