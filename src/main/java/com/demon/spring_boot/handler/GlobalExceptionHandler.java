package com.demon.spring_boot.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demon.spring_boot.entity.ErrorInfo;
import com.demon.spring_boot.exception.BaseException;

/**
 * <pre>
 * 全局的异常处理类
 * 
 * @ControllerAdvice 定义全局异常处理类
 * @ExceptionHandler 定义函数针对的异常类型
 * </pre>
 * 
 * @author xuliang
 * @since 2018年11月26日 下午4:30:31
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";
    
    /*@ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e){
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }*/
    
    /**
     * BaseException 异常时，返回json 格式的异常
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, BaseException e){
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("some data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
    
}
