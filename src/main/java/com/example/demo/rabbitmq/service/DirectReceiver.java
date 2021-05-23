package com.example.demo.rabbitmq.service;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author mr.monster
 * @version 1.0
 * @Description  监听消息
 * @date 2021/5/23 12:14
 */
@Component
@Slf4j
public class DirectReceiver {


    @RabbitListener(queues = "TestDirectQueue")
    public void TestDirectQueue(Map testMessage, Channel channel) throws IOException, IOException {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());


    }

}
