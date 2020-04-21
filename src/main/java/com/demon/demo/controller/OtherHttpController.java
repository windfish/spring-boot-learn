package com.demon.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * http 协议的post、put、delete 请求
 *
 * author: xuliang
 * since: 2020/4/20 14:33
 **/
@RestController
public class OtherHttpController {

    private Map<String, Object> params = new HashMap<>();

    @PostMapping("/v1/login")
    public Object login(String id, String pwd){
        params.clear();
        params.put("id", id);
        params.put("pwd", pwd);
        return params;
    }

    @PutMapping("/v1/put")
    public Object put(String id){
        params.clear();
        params.put("id", id);
        return params;
    }

    @DeleteMapping("/v1/del")
    public Object delete(String id){
        params.clear();
        params.put("id", id);
        return params;
    }

}
