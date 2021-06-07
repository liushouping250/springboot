package com.example.demo.modules.rabbitmq.service;

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
public class SendRabbitMsgService {

    @Autowired
    private DirectExchangeService directExchangeService;

    @Autowired
    private TopicExchangeService topicExchangeService;

    @Autowired
    private FanoutExchangeService fanoutExchangeService;

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
        topicExchangeService.sendRabbitExchangeMsg(msg);
    }

    /**
     *  发送消息到交换机上
     *
     * */
    public void  TestSendExchangeMsg1(String msg){
        topicExchangeService.sendRabbitExchangeMsg1(msg);
    }


    /**
     *  发送消息到扇形交换机上
     *
     * */
    public void   SendFanoutExchangeMsg(String msg){
        fanoutExchangeService.sendRabbitExchangeMsgA(msg);
    }

}
