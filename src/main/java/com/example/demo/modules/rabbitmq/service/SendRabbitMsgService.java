package com.example.demo.modules.rabbitmq.service;

import com.example.demo.rabbitmq.service.DirectExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/23 11:40
 */
@Service
public class SendRabbitMsgService {

    @Autowired
    private DirectExchangeService directExchangeService;

    /**
     *  发送消息到直连机
     *
     * */
    public void  TestSendDirectExchangeMsg(String msg){
        directExchangeService.sendRabbitDirectMsg(msg);
    }

    /**
     *  发送消息到交换机上
     *
     * */
    public void  TestSendExchangeMsg(String msg){
        directExchangeService.sendRabbitExchangeMsg(msg);
    }

    /**
     *  发送消息到交换机上
     *
     * */
    public void  TestSendExchangeMsg1(String msg){
        directExchangeService.sendRabbitExchangeMsg1(msg);
    }

}
