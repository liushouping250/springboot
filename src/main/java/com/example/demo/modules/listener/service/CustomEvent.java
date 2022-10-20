package com.example.demo.modules.listener.service;

import org.springframework.context.ApplicationEvent;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2022/10/12 16:52
 */
public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object source) {
        super(source);
    }

    public void printMessage(String msg){
        System.out.println("自定义事件：打印信息"+msg);
    }
}
