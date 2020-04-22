package com.demon.demo.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 服务器配置
 *
 * author: xuliang
 * since: 2020/4/22 14:25
 **/
@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "test.prop")
public class ServerSettings {

//    @Value("${name}")
    private String name;
//    @Value("${domain}")
    private String domain;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
