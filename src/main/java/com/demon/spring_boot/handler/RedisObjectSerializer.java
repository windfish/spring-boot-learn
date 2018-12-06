package com.demon.spring_boot.handler;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * redis 自定义的对象序列化接口
 * @author xuliang
 * @since 2018年12月6日 下午4:48:19
 *
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    /*
     * 使用spring 的序列化和反序列化
     * 有一个问题，序列化后，数据前会增加部分内容，造成存储长度增加，例如：12345abcd 序列化后是 \xac\xed\x00\x05t\x00\t12345abcd
     */
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserialzer = new DeserializingConverter();
    
    static final byte[] EMPTY_ARRAY = new byte[0];
    
    @Override
    public Object deserialize(byte[] data) throws SerializationException {
        if(isEmpty(data)){
            return null;
        }
        return deserialzer.convert(data);
    }

    @Override
    public byte[] serialize(Object obj) throws SerializationException {
        if(obj == null){
            return EMPTY_ARRAY;
        }
        return serializer.convert(obj);
    }
    
    private boolean isEmpty(byte[] data){
        return data == null || data.length == 0;
    }

}
