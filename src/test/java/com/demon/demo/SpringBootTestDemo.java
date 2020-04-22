package com.demon.demo;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * author: xuliang
 * since: 2020/4/22 15:43
 **/
@RunWith(SpringRunner.class)    // 底层用JUnit  继承 SpringJUnit4ClassRunner
@SpringBootTest(classes = {Application.class})
public class SpringBootTestDemo {

    @Test
    public void testOne(){
        System.out.println("test in SpringBoot.");
        TestCase.assertEquals(1, 1);
        Assert.assertEquals(1, 1);
    }

    @Before
    public void testBegin(){
        System.out.println("do something before test.");
    }

    @After
    public void testAfter(){
        System.out.println("do something after test.");
    }

}
