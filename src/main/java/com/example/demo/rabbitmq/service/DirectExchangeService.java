package com.example.demo.rabbitmq.service;

import com.example.demo.rabbitmq.RabbitMqEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        log.info("发送rabbit消息===="+msg);
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",msg);

    }

    //推送消息到交换机上
    public void sendRabbitExchangeMsg(String msg){
        log.info("发送rabbit消息到交换机===="+msg);
        rabbitTemplate.convertAndSend(RabbitMqEnum.TopicExchange.getName(),RabbitMqEnum.TopicTest1.getName(),msg);

    }

    public void sendRabbitExchangeMsg1(String msg){
        log.info("发送rabbit消息到交换机通配符===="+msg);
        rabbitTemplate.convertAndSend(RabbitMqEnum.TopicExchange.getName(),RabbitMqEnum.TopicTest2.getName(),msg);

    }



}
