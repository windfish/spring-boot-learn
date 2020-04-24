package com.demon.demo.handler;

import com.demon.demo.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * author: xuliang
 * since: 2020/4/23 14:23
 **/
@ControllerAdvice
public class CustomExtHandler {

    // 捕获全局异常
    @ExceptionHandler(value = Exception.class)
    Object HandlerException(Exception e, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("url", request.getRequestURL());
        mav.addObject("exception", e);
        return mav;

    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    Object HandlerMyException(Exception e){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("msg", e.getMessage());
        return map;
    }

}
