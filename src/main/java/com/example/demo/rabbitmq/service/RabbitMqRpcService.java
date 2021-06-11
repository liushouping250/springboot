package com.example.demo.rabbitmq.service;import com.fasterxml.jackson.databind.ObjectMapper;import com.rabbitmq.client.*;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.autoconfigure.amqp.RabbitProperties;import org.springframework.stereotype.Service;import java.io.IOException;import java.util.Arrays;import java.util.concurrent.TimeoutException;@Service@Slf4jpublic class RabbitMqRpcService {    private Connection connection;    private Channel channel;    public  RabbitMqRpcService(RabbitProperties rabbitProperties) throws IOException, TimeoutException {        ConnectionFactory factory = new ConnectionFactory();        factory.setHost(rabbitProperties.getHost());        factory.setPort(rabbitProperties.getPort());        factory.setRequestedHeartbeat(60);        factory.setAutomaticRecoveryEnabled(true);        factory.setUsername(rabbitProperties.getUsername());        factory.setPassword(rabbitProperties.getPassword());        factory.setConnectionTimeout(5000);        connection = factory.newConnection();        channel = connection.createChannel();    }    void server(String rpcQueName, String method) {        try {            log.info("服务器启动成功，正等待客户端RPC请求......" + rpcQueName);            channel.queueDeclare(rpcQueName, false, false, false, null);            channel.queuePurge(rpcQueName);            channel.basicQos(1);            DeliverCallback deliverCallback = (consumerTag, delivery) -> {                AMQP.BasicProperties replyProps = new AMQP.BasicProperties                        .Builder()                        .correlationId(delivery.getProperties().getCorrelationId())                        .replyTo(delivery.getProperties().getReplyTo())                        .build();                Object response = "";                byte[] aByte = null;                try {                    String message = Arrays.toString(delivery.getBody());                    log.info("服务端收到的RPC请求：" + message);                    log.info(rpcQueName);                    String res = "ressss";                    response = res.getBytes();                    log.info("响应数据:{}", response);                } catch (RuntimeException  e) {                    e.printStackTrace();                } finally {                    channel.basicPublish("", replyProps.getReplyTo(), replyProps, aByte);                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);                }            };            channel.basicConsume(rpcQueName, false, deliverCallback, (consumerTag -> {            }));        } catch (IOException e) {            e.printStackTrace();        }    }}