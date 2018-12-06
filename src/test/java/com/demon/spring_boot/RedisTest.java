package com.demon.spring_boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.demon.spring_boot.entity.UserRedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    /*
     * redis 字符串操作类
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("testSpringBoot", "hello spring boot");
        Assert.assertEquals("hello spring boot", stringRedisTemplate.opsForValue().get("testSpringBoot"));
    }
    
    /*
     * redis 自定义对象操作类
     */
    @Autowired
    @Qualifier("objectRedisTemplate")
    private RedisTemplate<String, UserRedis> redisTemplate;
    @Autowired
    @Qualifier("objectRedisTemplate")
    private RedisTemplate<String, String> stringRedisTemplate2;
    
    @Test
    public void testObj(){
        UserRedis user = new UserRedis("蝙蝠侠", 45);
        redisTemplate.opsForValue().set(user.getUsername(), user);
        
        user = new UserRedis("超人", 26);
        redisTemplate.opsForValue().set(user.getUsername(), user);
        
        user = new UserRedis("蜘蛛侠", 20);
        redisTemplate.opsForValue().set(user.getUsername(), user);
        
        Assert.assertEquals(26, redisTemplate.opsForValue().get("超人").getAge().intValue());
        
        stringRedisTemplate2.opsForValue().set("testObj", "12345abcd");
        Assert.assertEquals("12345abcd", stringRedisTemplate2.opsForValue().get("testObj"));
    }
    
}
