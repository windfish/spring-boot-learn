package com.demon.spring_boot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiDataSourceTest {
    
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;
    
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;
    
    @Before
    public void setUp(){
        primaryJdbcTemplate.update("delete from user");
        secondaryJdbcTemplate.update("delete from user");
    }
    
    @Test
    public void test(){
        primaryJdbcTemplate.update("insert into user(id,name,age) values(?,?,?)", 1, "aaa", 23);
        primaryJdbcTemplate.update("insert into user(id,name,age) values(?,?,?)", 2, "bbb", 26);
        
        secondaryJdbcTemplate.update("insert into user(id,name,age) values(?,?,?)", 1, "bbb", 26);
        
        Assert.assertEquals("2", primaryJdbcTemplate.queryForObject("select count(1) from user", String.class));
        
        Assert.assertEquals("1", secondaryJdbcTemplate.queryForObject("select count(1) from user", String.class));
    }

}
