package com.demon.spring_boot.service;

import com.demon.spring_boot.entity.User;

public interface UserService {

    void insert(User user);
    
    void removeById(Long id);
    
    Integer countUser();
    
    void removeAllUser();
    
}
