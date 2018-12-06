package com.demon.spring_boot.entity;

import java.io.Serializable;

public class UserRedis implements Serializable {

    private static final long serialVersionUID = -2811404327672330522L;

    private String username;
    private Integer age;
    
    public UserRedis() {
    }

    public UserRedis(String username, Integer age) {
        super();
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
}
