package com.demon.spring_boot.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * lombok 生成对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class LocalDateDto implements Serializable {

    private static final long serialVersionUID = -6361712675142238515L;
    
    private String userName;
    private LocalDate birthday;
    
}
