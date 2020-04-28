package com.demon.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: xuliang
 * since: 2020/4/28 14:43
 **/
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("add")
    public Object add(){
        redisTemplate.opsForValue().set("test_name", "SpringBoot redis");
        return "OK";
    }

    @GetMapping("get")
    public Object get(){
        return redisTemplate.opsForValue().get("test_name");
    }

}
