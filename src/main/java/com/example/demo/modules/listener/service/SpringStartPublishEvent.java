package com.example.demo.modules.listener.service;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2022/10/12 17:32
 */
@Component
public class SpringStartPublishEvent implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.printf("SpringStartPublishEvent:启动完成======");
    }
}
