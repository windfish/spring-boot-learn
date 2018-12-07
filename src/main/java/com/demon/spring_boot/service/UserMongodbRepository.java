package com.demon.spring_boot.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demon.spring_boot.entity.UserMongodb;

public interface UserMongodbRepository extends MongoRepository<UserMongodb, Long> {

    UserMongodb findByUsername(String username);
    
}
