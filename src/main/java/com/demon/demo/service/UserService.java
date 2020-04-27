package com.demon.demo.service;

import com.demon.demo.entity.User;

import java.util.List;

public interface UserService {

    public int add(User user);

    public List<User> getAll();

    public User findById(int id);

}
