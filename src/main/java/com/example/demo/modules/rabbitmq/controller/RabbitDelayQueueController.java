package com.example.demo.modules.rabbitmq.controller;

import com.example.demo.config.global_exception.ResultUtil;
import com.example.demo.modules.rabbitmq.service.SendRabbitDelayQueueService;
import com.example.demo.modules.rabbitmq.service.SendRabbitRpcMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @description: rabbit rpc测试
 * @author: Mr.monster.liu
 * @create: 2021-06-07 14:03
 **/
@RestController
@RequestMapping("api/rabbit/delay")
@Api(tags = "延迟消息测试")
public class RabbitDelayQueueController {

    @Autowired
    private SendRabbitDelayQueueService sendRabbitDelayQueueService;


    @GetMapping("/test")
    @ApiOperation(value = "延迟消息测试")
    public ResultUtil<Object> index(@Valid @ApiParam(name = "msg", value = "发送消息", required = true) @RequestParam("msg") String msg ,@RequestParam("time") Long time){
        return  ResultUtil.success(sendRabbitDelayQueueService.testSendDelayMsg(msg,time));
    }



}
