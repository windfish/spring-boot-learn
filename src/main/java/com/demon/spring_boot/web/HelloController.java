package com.demon.spring_boot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demon.spring_boot.exception.BaseException;

import springfox.documentation.annotations.ApiIgnore;

@RestController
public class HelloController {

    @ApiIgnore
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World.";
    }
    
    @ApiIgnore
    @RequestMapping("/testError")
    public String error() throws Exception{
        throw new Exception("发生异常");
    }
    
    @ApiIgnore
    @RequestMapping("/jsonError")
    public String json() throws BaseException {
        throw new BaseException("自定义异常");
    }
    
}
