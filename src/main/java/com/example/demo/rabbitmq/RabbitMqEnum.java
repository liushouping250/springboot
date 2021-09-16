package com.example.demo.rabbitmq;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/24 21:52
 */
public enum RabbitMqEnum {
    /**
     *  rabbit enum
     */
    TestDirectQueue("TestDirectQueue","测试直连机消息队列名称"),
    TestDirectExchange("TestDirectExchange","测试直连机消息交换机名称"),
    TestDirectRouting("TestDirectRouting","测试直连机消息路由名称"),

    TopicTest1("topic.test1","测试直连机消息队列名称"),
    TopicTest2("topic.test2","测试直连机消息队列名称"),
    TopicExchange("topicExchange","交换机交换机名称"),
    //定的键值为用上通配路由键规则topic.#
    TopicAllExchange("topic.#","交换机交换机名称"),
    FanoutExchange("fanoutExchange","扇形交换机交换机名称"),
    FanoutQueueA("fanout.queue.a","扇形交换机队列名称"),
    FanoutQueueB("fanout.queue.b","扇形交换机队列名称"),
    FanoutQueueC("fanout.queue.c","扇形交换机队列名称");


    private  String name;

    private String remark;

    RabbitMqEnum(String name,String remark){
        this.name =name;
        this.remark=remark;
    }

    public String getName(){
        return  this.name;
    }

    public String getRemark(){
        return  this.remark;
    }



}
