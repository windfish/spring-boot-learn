package com.demon.spring_boot.exception;

/**
 * 自定义异常
 * @author xuliang
 * @since 2018年11月26日 下午4:58:39
 *
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = 4241546137364449642L;

    public BaseException(String message) {
        super(message);
    }
    
}
