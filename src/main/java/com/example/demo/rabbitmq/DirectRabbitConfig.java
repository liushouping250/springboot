//package com.example.demo.rabbitmq;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author mr.monster
// * @version 1.0
// * @Description rabbitmq 交换机
// * @date 2021/5/23 11:17
// */
//@Configuration
//public class DirectRabbitConfig {
//
//
//    /**
//     * 队列 起名：TestDirectQueue
//     */
//    @Bean
//    public Queue testDirectQueue(){
//        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
//        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
//        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
//        return new Queue(RabbitMqEnum.TestDirectQueue.getName(),true);
//    }
//
//    /**
//     * Direct交换机 起名：TestDirectExchange
//     * @return DirectExchange
//     */
//    @Bean
//    DirectExchange testDirectExchange() {
//        return new DirectExchange(RabbitMqEnum.TestDirectExchange.getName(),true,false);
//    }
//
//    /**
//     * 绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
//     * @return Binding
//     */
//    @Bean
//    Binding bindingDirect() {
//        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with(RabbitMqEnum.TestDirectRouting.getName());
//    }
//
//
//
//
//}
