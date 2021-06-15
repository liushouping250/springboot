package com.example.demo.rabbitmq.service;

import com.example.demo.util.RabbitRpcBuilderUtil;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class RabbitMqRpcService {

    private Connection connection;

    private Channel channel;

    public  RabbitMqRpcService(RabbitProperties rabbitProperties) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitProperties.getHost());
        factory.setPort(rabbitProperties.getPort());
        factory.setRequestedHeartbeat(60);
        factory.setAutomaticRecoveryEnabled(true);
        factory.setUsername(rabbitProperties.getUsername());
        factory.setPassword(rabbitProperties.getPassword());
        factory.setConnectionTimeout(5000);
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    void server(String rpcQueName, String method) {
        try {
            log.info("正等待客户端RPC请求==" + rpcQueName);
            channel.queueDeclare(rpcQueName, false, false, false, null);
            channel.queuePurge(rpcQueName);
            channel.basicQos(1);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .replyTo(delivery.getProperties().getReplyTo())
                        .build();
                RabbitRpcBuilderUtil response = null;
                byte[] aByte = null;
                try {
                    RabbitRpcBuilderUtil rabbitRpcBuilderUtil = RabbitRpcBuilderUtil.bytesToObject(delivery.getBody());
                    String data = rabbitRpcBuilderUtil.getData();
                    RabbitRpcBuilderUtil.Header header = rabbitRpcBuilderUtil.getHeader();
                    log.info(rpcQueName+"服务端收到的RPC请求：" + data);
                    response = new RabbitRpcBuilderUtil(data,header).build();
                    log.info("响应数据:{}", response);
                    aByte = RabbitRpcBuilderUtil.objectToBytes(response);
                } catch (RuntimeException | ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    channel.basicPublish("", replyProps.getReplyTo(), replyProps, aByte);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(rpcQueName, false, deliverCallback, (consumerTag -> {
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
