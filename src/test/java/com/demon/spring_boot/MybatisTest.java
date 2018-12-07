package com.demon.spring_boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.demon.spring_boot.entity.UserMybatis;
import com.demon.spring_boot.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;
    
    @Test
    @Rollback
    @Transactional("shopTransactionManager")
    public void test(){
        userMapper.insert(new UserMybatis(100L, "xzxzxz", 15));
        UserMybatis user = userMapper.findByName("xzxzxz");
        System.out.println(JSON.toJSONString(user));
        Assert.assertEquals(15, user.getAge().intValue());
    }
    
}
