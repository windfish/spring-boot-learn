package com.demon.demo.service.impl;

import com.demon.demo.entity.User;
import com.demon.demo.mapper.UserMapper;
import com.demon.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: xuliang
 * since: 2020/4/27 15:44
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }


}
