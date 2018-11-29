package com.demon.spring_boot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demon.spring_boot.entity.User;
import com.demon.spring_boot.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;
    
    @Before
    public void setUp(){
        userService.removeAllUser();
    }
    
    @Test
    public void test(){
        userService.insert(new User(1L, "a", 11));
        userService.insert(new User(2L, "b", 11));
        userService.insert(new User(3L, "c", 11));
        userService.insert(new User(4L, "d", 11));
        userService.insert(new User(5L, "e", 11));
        
        Assert.assertEquals(5, userService.countUser().intValue());
        
        userService.removeById(2L);
        userService.removeById(4L);
        
        Assert.assertEquals(3, userService.countUser().intValue());
    }
    
}
