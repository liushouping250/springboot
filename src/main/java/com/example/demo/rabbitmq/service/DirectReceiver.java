package com.example.demo.rabbitmq.service;

import com.example.demo.rabbitmq.RabbitMqEnum;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author mr.monster
 * @version 1.0
 * @Description  直联机监听消息
 * @date 2021/5/23 12:14
 */
@Component
@Slf4j
public class DirectReceiver {

    @RabbitListener(queues = "TestDirectQueue")
    public void TestDirectQueue(Message testMessage,Channel channel) throws IOException {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
        channel.basicAck(testMessage.getMessageProperties().getDeliveryTag(), true);
    }
}
