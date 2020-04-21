package com.demon.demo.controller;

import com.demon.demo.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * author: xuliang
 * since: 2020/4/20 10:44
 **/
@RestController
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "Hello, SpringBoot";
    }

    @RequestMapping("/test_map")
    public Map<String, String> testRestController(){
        Map<String, String> map = new HashMap<>();
        map.put("test1", "adadsadasd");
        map.put("test2", "asd123123");
        return map;
    }

    @RequestMapping("/test_json")
    public User testJson(){
        return new User(23, "123123123", "1385454454", new Date());
    }

}
