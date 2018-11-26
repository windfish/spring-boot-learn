package com.demon.spring_boot.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomProperties {

    /*
     * 通过 @Value("${属性名}") 加载对应的配置文件属性
     */
    
    @Value("${custom.name}")
    private String name;
    @Value("${custom.title}")
    private String title;
    @Value("${custom.desc}")
    private String desc;
    
    @Value("${custom.value}")
    private String value;
    @Value("${custom.int}")
    private int number;
    @Value("${custom.long}")
    private long bigNumber;
    @Value("${custom.random10}")
    private int test1;
    @Value("${custom.random10-20}")
    private int test2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getBigNumber() {
        return bigNumber;
    }

    public void setBigNumber(long bigNumber) {
        this.bigNumber = bigNumber;
    }

    public int getTest1() {
        return test1;
    }

    public void setTest1(int test1) {
        this.test1 = test1;
    }

    public int getTest2() {
        return test2;
    }

    public void setTest2(int test2) {
        this.test2 = test2;
    }
    
}
