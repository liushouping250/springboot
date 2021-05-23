package com.example.demo.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * @author mr.monster
 * @version 1.0
 * @Description  rabbitmq 直连
 * @date 2021/5/23 11:32
 */
@Service
@Slf4j
public class DirectExchangeService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //推送消息到直连机
    public void sendRabbitDirectMsg(String msg){
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",msg);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        log.info("发送rabbit消息===="+msg);
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",map);

    }



}
