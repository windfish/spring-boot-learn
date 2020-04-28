package com.demon.demo.controller;

import com.demon.demo.entity.User;
import com.demon.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * author: xuliang
 * since: 2020/4/27 15:46
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("add")
    public Object add(){
        User user = new User();
        user.setName("test");
        user.setPhone("12313123123");
        user.setAge(15);
        user.setCreateTime(new Date());
        int id = userService.add(user);
        return id;
    }

    @GetMapping("get_all_user")
    public Object getAll(){
        return userService.getAll();
    }

    @GetMapping("find_by_id/{id}")
    public Object findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @GetMapping("test_tx")
    public Object testTransation(){
        return userService.addAccount();
    }

}
