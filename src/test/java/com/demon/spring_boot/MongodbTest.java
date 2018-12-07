package com.demon.spring_boot;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.demon.spring_boot.entity.UserMongodb;
import com.demon.spring_boot.service.UserMongodbRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTest {

    @Autowired
    private UserMongodbRepository mongodbRepository;
    
    @Before
    public void setUp(){
        mongodbRepository.deleteAll();
    }
    
    @Test
    public void test(){
        mongodbRepository.save(new UserMongodb(1L, "xxx", 27));
        mongodbRepository.save(new UserMongodb(2L, "aaa", 35));
        mongodbRepository.save(new UserMongodb(3L, "bbb", 20));
        
        UserMongodb u = new UserMongodb();
        u.setId(2L);
        u = mongodbRepository.findOne(Example.of(u)).get();
        mongodbRepository.delete(u);
        Assert.assertEquals(2, mongodbRepository.count());
        
        List<UserMongodb> users = mongodbRepository.findAll();
        System.out.println(JSON.toJSONString(users, true));
        
        u = mongodbRepository.findByUsername("xxx");
        System.out.println(JSON.toJSONString(u));
    }
    
}
