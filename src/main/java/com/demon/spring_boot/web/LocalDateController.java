package com.demon.spring_boot.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demon.spring_boot.entity.LocalDateDto;

@RestController
public class LocalDateController {

    @PostMapping("/localdto")
    public LocalDateDto errorDate(@RequestBody LocalDateDto dto){
        return dto;
    }
    
}
