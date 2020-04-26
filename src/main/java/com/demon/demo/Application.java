package com.demon.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
  *
  * author: xuliang
  * since: 2020/4/14 10:44
  **/
@SpringBootApplication
@ServletComponentScan(value = {"com.demon.demo.filter", "com.demon.demo.servlet", "com.demon.demo.listener"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
