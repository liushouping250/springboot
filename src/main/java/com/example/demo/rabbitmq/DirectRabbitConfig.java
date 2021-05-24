package com.example.demo.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mr.monster
 * @version 1.0
 * @Description rabbitmq 交换机
 * @date 2021/5/23 11:17
 */
@Configuration
public class DirectRabbitConfig {




    //队列 起名：TestDirectQueue
    @Bean
    public Queue TestDirectQueue(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        return new Queue(RabbitMqEnum.TestDirectQueue.getName(),true);
    }

    //Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange(RabbitMqEnum.TestDirectExchange.getName(),true,false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with(RabbitMqEnum.TestDirectRouting.getName());
    }

    @Bean
    public Queue firstQueue() {
        return new Queue(RabbitMqEnum.TopicTest1.getName());
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(RabbitMqEnum.TopicTest2.getName());
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RabbitMqEnum.TopicExchange.getName());
    }


    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(RabbitMqEnum.TopicTest1.getName());
    }

    /*@Bean
    Binding bindingExchangeMessage1() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with(RabbitMqEnum.TopicTest2.getName());
    }*/

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    /*@Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with(RabbitMqEnum.TopicTest2.getName() );
    }*/


}
