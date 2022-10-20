package com.example.demo.modules.listener.service;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2022/10/12 17:36
 */
@Component
@Order(1)
public class SpringStartPublishEventCopy implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.printf("SpringStartPublishEventCopy:启动完成======");
    }
}
