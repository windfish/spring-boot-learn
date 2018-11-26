package com.demon.spring_boot.entity;

/**
 * JSON 格式的异常信息
 * @author xuliang
 * @since 2018年11月26日 下午4:55:52
 *
 */
public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;
    
    private Integer code;
    private String message;
    private String url;
    private T data;
    
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    
}
