package com.example.demo.modules.listener.service;

import com.example.demo.modules.listener.controller.ListenerController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2022/10/9 22:58
 */
@Component
public class EvenListener {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @EventListener
    public void evnetList(CustomEvent event) throws JsonProcessingException {
        Object source = event.getSource();
        ListenerController.User reader = objectMapper.readValue(source.toString(), ListenerController.User.class);
        event.printMessage("名称："+reader.getName()+" \n 用户ID: "+reader.getUserId());
    }




}
