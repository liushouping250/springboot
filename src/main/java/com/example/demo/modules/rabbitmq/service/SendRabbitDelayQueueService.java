package com.example.demo.modules.rabbitmq.service;

import com.example.demo.rabbitmq.service.DelayQueueService;
import com.example.demo.rabbitmq.service.DirectExchangeService;
import com.example.demo.rabbitmq.service.FanoutExchangeService;
import com.example.demo.rabbitmq.service.TopicExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/23 11:40
 */
@Service
public class SendRabbitDelayQueueService {



    @Autowired
    private DelayQueueService delayQueueService;

    /**
     *  发送消息延迟消息
     *
     * */
    public String  testSendDelayMsg(String msg,  long delayTimes){
        delayQueueService.sendMessage(msg,delayTimes);
        return msg;
    }






}
