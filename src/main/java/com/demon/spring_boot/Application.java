package com.demon.spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public ObjectMapper serializingObjectMapper(){
	    ObjectMapper om = new ObjectMapper();
	    om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    om.registerModule(new JavaTimeModule());
	    return om;
	}
	
}
