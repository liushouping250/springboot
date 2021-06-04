package com.example.demo.rabbitmq;

import com.example.demo.config.ApplicationProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/25 23:03
 */
 @Service
public class RabbitMqRpcConfig {

    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topic.exchange";


    @Autowired
    ConnectionFactory connectionFactory;

    @Autowired
    private ApplicationProperties applicationProperties;


    @Bean(name = "connectionFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(applicationProperties.getRabbit().getHost());
        connectionFactory.setPort(applicationProperties.getRabbit().getPort());
        connectionFactory.setUsername(applicationProperties.getRabbit().getUsername());
        connectionFactory.setPassword(applicationProperties.getRabbit().getPassword());
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //设置reply_to（返回队列，只能在这设置）
        rabbitTemplate.setReplyAddress(TOPIC_QUEUE2);
        rabbitTemplate.setReplyTimeout(60000);
        return rabbitTemplate;
    }
    //返回队列监听器（必须有）
    @Bean(name="replyMessageListenerContainer")
    public SimpleMessageListenerContainer createReplyListenerContainer() {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setQueueNames(TOPIC_QUEUE2);
        listenerContainer.setMessageListener(rabbitTemplate());
        return listenerContainer;
    }



    //创建队列
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE1);
    }
    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE2);
    }

    //创建交换机
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    //交换机与队列进行绑定
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(TOPIC_QUEUE1);
    }
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(TOPIC_QUEUE2);
    }

}
