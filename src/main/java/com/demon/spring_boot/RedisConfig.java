package com.demon.spring_boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.demon.spring_boot.handler.RedisObjectSerializer;

/**
 * redis 自定义对象序列化配置
 * 
 * @author xuliang
 * @since 2018年12月6日 下午5:19:01
 *
 */
@Configuration
public class RedisConfig {

    @Bean(name="objectRedisTemplate")
    public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
    
}
