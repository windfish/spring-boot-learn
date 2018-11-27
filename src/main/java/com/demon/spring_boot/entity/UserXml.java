package com.demon.spring_boot.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName="user")
public class UserXml {

    @JacksonXmlProperty(localName="name")
    private String name;
    @JacksonXmlProperty(localName="age")
    private Integer age;
    
}
