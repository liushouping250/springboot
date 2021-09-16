package com.example.demo.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  延迟队列
 * @Description rabbitmq 延迟队列  手动确认 重新入队 是加入到队列前面  容易造成消息阻塞
 * @author mr.monster
 * @version 1.0
 * @date 2021/5/23 11:17
 */
@Configuration
public class DelayQueueRabbitConfig {


    /**
     * 发送消息通知队列所绑定的->交换机
     */
   /* @Bean
    DirectExchange sendMsgDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(DelayQueueEnum.QUEUE_MSG_SEND.getExchange())
                .durable(true)
                .build();
    }*/

    /**
     *  发送通知的->消费队列
     */
    /*@Bean
    public Queue sendMsgQueue() {
        return new Queue(DelayQueueEnum.QUEUE_MSG_SEND.getRouteKey());
    }*/

    /**
     * 1.0将发送短信 队列绑定到->交换机
     */
   /* @Bean
    Binding sendSmsBinding(DirectExchange sendMsgDirect, Queue sendMsgQueue){
        return BindingBuilder
                .bind(sendMsgQueue)
                .to(sendMsgDirect)
                .with(DelayQueueEnum.QUEUE_MSG_SEND.getRouteKey());
    }*/

    /**
     * 2.0发送邮件消息通知队列所绑定的->交换机
     */
    /*@Bean
    DirectExchange sendEmailDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(DelayQueueEnum.QUEUE_EMAIL_SEND.getExchange())
                .durable(true)
                .build();
    }*/

    /**
     * 2.0发送邮件的->消费队列
     */
    /*@Bean
    public Queue sendEmailQueue() {
        return new Queue(DelayQueueEnum.QUEUE_EMAIL_SEND.getRouteKey());
    }*/

    /**
     * 2.0将发送邮件 队列绑定到->交换机
     */
    /*@Bean
    Binding sendEmailBinding(DirectExchange sendEmailDirect, Queue sendEmailQueue){
        return BindingBuilder
                .bind(sendEmailQueue)
                .to(sendEmailDirect)
                .with(DelayQueueEnum.QUEUE_EMAIL_SEND.getRouteKey());
    }*/

    /*======================== 死信队列绑定  ==========================*/
    /**
     * 订单延迟队列队列所绑定的->交换机
     */
    @Bean
    DirectExchange bindingTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(DelayQueueEnum.QUEUE_TTL_DIRECT_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 延迟队列（死信队列）
     */
    @Bean
    public Queue bindingTtlQueue() {
        return QueueBuilder
                .durable(DelayQueueEnum.QUEUE_TTL_DIRECT_CANCEL.getName())
                //到期后转发的交换机
                .withArgument("x-dead-letter-exchange", RabbitMqEnum.FanoutExchange.getName())
                //到期后转发的路由键
               // .withArgument("x-dead-letter-routing-key", DelayQueueEnum.QUEUE_MSG_SEND.getRouteKey())
                .build();
    }

    /**
     * 4.0将订单延迟队列绑定到交换机
     */
    @Bean
    Binding orderTtlBinding(DirectExchange bindingTtlDirect,Queue bindingTtlQueue){
        return BindingBuilder
                .bind(bindingTtlQueue)
                .to(bindingTtlDirect)
                .with(DelayQueueEnum.QUEUE_TTL_DIRECT_CANCEL.getRouteKey());
    }

    /*======================== 死信队列绑定  ==========================*/

}
