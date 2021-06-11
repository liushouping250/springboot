package com.example.demo.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/25 23:03
 */
@Service
@Slf4j
public class RabbitMqRpcConfig   {

    private Connection connection;

    private Channel channel;

    public RabbitMqRpcConfig(RabbitProperties rabbitProperties) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置心跳检测
        connectionFactory.setRequestedHeartbeat(60);
        //设置自动恢复
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setHost(rabbitProperties.getHost());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
    }
    //发送RPC请求
    public String call(String requestQueueName, String message,String expirationTime,Integer responseTime) throws IOException, InterruptedException {
        String replyQueueName = channel.queueDeclare().getQueue();
        log.info(replyQueueName+"___________________________________________");
        final String corrId = UUID.randomUUID().toString();
        log.info("uuid="+corrId);
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .expiration(expirationTime)
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        channel.basicPublish("", requestQueueName, props, message.getBytes("utf-8"));
        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);
        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            log.info("=========="+delivery.getProperties().getCorrelationId());
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                log.info(delivery.toString());
                response.offer(Arrays.toString(delivery.getBody()));
            }
        }, consumerTag -> {
        });
        String result = response.poll(responseTime, TimeUnit.MILLISECONDS);
        channel.basicCancel(ctag);
        return result;
    }

    public void close() throws IOException {
        connection.close();
    }
}
