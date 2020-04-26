package com.demon.demo.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * author: xuliang
 * since: 2020/4/26 15:21
 **/
public class TestInterceptor implements HandlerInterceptor {

    /**
     * 进入Controller 之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("TestInterceptor --> preHandle");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 调用完Controller 之后，视图渲染之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("TestInterceptor --> postHandle");
    }

    /**
     * 整个请求完成之后，通常用于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("TestInterceptor --> afterCompletion");
    }
}
