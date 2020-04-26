package com.demon.demo.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * author: xuliang
 * since: 2020/4/26 14:39
 **/
@WebListener
public class MyRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("------requestDestroyed--------");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("------requestInitialized--------");
    }
}
