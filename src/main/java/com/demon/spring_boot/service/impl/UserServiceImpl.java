package com.demon.spring_boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.demon.spring_boot.entity.User;
import com.demon.spring_boot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void insert(User user) {
        jdbcTemplate.update("insert into user(id, name, age) values(?, ?, ?)", user.getId(), user.getName(), user.getAge());
    }

    @Override
    public void removeById(Long id) {
        
    }

    @Override
    public Integer countUser() {
        return null;
    }

    @Override
    public void removeAllUser() {
        
    }

}
