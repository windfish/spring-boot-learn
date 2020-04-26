package com.demon.demo.filter;

import com.demon.demo.exception.MyException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义Filter，使用Servlet 3.0 的注解
 *
 * author: xuliang
 * since: 2020/4/26 13:35
 **/
@WebFilter(urlPatterns = "/test_filter/*", filterName = "myTestFilter")
public class MyTestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init MyTestFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter MyTestFilter");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String username = req.getParameter("username");
        if("demon".equals(username)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            return;
        }

    }

    @Override
    public void destroy() {
        System.out.println("destroy MyTestFilter");
    }

}
