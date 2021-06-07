package com.example.demo.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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
public class RabbitMqRpcConfig implements AutoCloseable {

    private Connection connection;

    private Channel channel;


    /**
     * rabbitmq的有参构造器
     * @param rabbitProperties
     * @throws IOException
     * @throws TimeoutException
     */
    public void RabbitMqRpcConfig(RabbitProperties rabbitProperties) throws IOException, TimeoutException {
        com.rabbitmq.client.ConnectionFactory connectionFactory = new ConnectionFactory();
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

    /**
     * 向队列发消息并取回消息
     * @param requestQueueName
     * @param message
     * @param
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String call(String requestQueueName, byte[] message,String expirationTime,Integer responseTime) throws IOException, InterruptedException {
        String replyQueueName = channel.queueDeclare().getQueue();
        log.info("RPC队列名称===="+replyQueueName);
        final String corrId = UUID.randomUUID().toString();
        log.info("RPC uuid="+corrId);
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .expiration(expirationTime)
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        channel.basicPublish("", requestQueueName, props, message);
        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);
        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                log.info(Arrays.toString(delivery.getBody()));
                //response.offer(delivery.getBody());
            }
        }, consumerTag -> {
        });
        String result = response.poll(responseTime, TimeUnit.MILLISECONDS);
        log.info(requestQueueName+result+"___@@@_______________________________________________");
        channel.basicCancel(ctag);
        return result;
    }
    @Override
    public void close() throws Exception {

    }
}
