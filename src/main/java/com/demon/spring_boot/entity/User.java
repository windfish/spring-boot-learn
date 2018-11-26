package com.demon.spring_boot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("user 对象")
public class User {

    @ApiModelProperty("用户ID")
    private Long id;
    @ApiModelProperty("用户名称")
    private String name;
    @ApiModelProperty("用户年龄")
    private Integer age;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    
}
