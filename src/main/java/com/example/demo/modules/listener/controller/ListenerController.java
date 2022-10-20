package com.example.demo.modules.listener.controller;

import com.example.demo.modules.listener.service.CustomEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2022/10/12 16:56
 */
@Controller
@RequestMapping("/listener")
public class ListenerController {

    @Autowired
    private ApplicationEventPublisher publisher;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/test")
    public  void demo() throws JsonProcessingException {
        User user = new User();
        user.setName("名称");
        user.setUserId(12);
        publisher.publishEvent(new CustomEvent(objectMapper.writeValueAsString(user)));

    }

    @Data
    public static class User{

        private String name;

        private Integer userId;

    }
}
