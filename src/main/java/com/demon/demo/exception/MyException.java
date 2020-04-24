package com.demon.demo.exception;

/**
 * author: xuliang
 * since: 2020/4/23 14:38
 **/
public class MyException extends RuntimeException {

    private String code;
    private String msg;

    public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
