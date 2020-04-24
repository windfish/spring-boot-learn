package com.demon.demo.controller;

import com.demon.demo.exception.MyException;
import com.demon.demo.vo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 异常测试
 *
 * author: xuliang
 * since: 2020/4/23 14:17
 **/
@RestController
public class ExceptionController {

    @RequestMapping("/v1/test_exp")
    public Object index(){
        int i = 1/0;
        return new User(28, "2131313", "13800000233", new Date());
    }

    @RequestMapping("/v1/my_exp")
    public Object myExp(){
        throw new MyException("100", "my exception");
    }

}
