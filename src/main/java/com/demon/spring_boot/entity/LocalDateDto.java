package com.demon.spring_boot.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class LocalDateDto implements Serializable {

    private static final long serialVersionUID = -6361712675142238515L;
    
    private String userName;
    private LocalDate birthday;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
}
