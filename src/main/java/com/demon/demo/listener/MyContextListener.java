package com.demon.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * author: xuliang
 * since: 2020/4/26 14:48
 **/
@WebListener
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("-----------contextInitialized-----------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("-----------contextDestroyed-----------");
    }
}
