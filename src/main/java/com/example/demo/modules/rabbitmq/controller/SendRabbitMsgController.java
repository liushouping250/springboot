package com.example.demo.modules.rabbitmq.controller;

import com.example.demo.config.global_exception.ResultUtil;
import com.example.demo.modules.rabbitmq.service.SendRabbitMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author mr.monster
 * @version 1.0
 * @Description  发送rabbit 消息
 * @date 2021/5/23 11:44
 */
@RestController
@RequestMapping("/api/rabbit")
@Api(tags = "测试rabbit相关")
@Slf4j
public class SendRabbitMsgController {

    @Autowired
    private SendRabbitMsgService  sendRabbitMsgService;

    @GetMapping("/test")
    @ApiOperation(value = "测试直连机发送消息")
    public ResultUtil<Object> test(@Valid @ApiParam(name = "msg", value = "发送消息", required = true) @RequestParam("msg") String msg){
        sendRabbitMsgService.TestSendDirectExchangeMsg(msg);
        return ResultUtil.success();
    }

    @GetMapping("/exchange")
    @ApiOperation(value = "测试交换机发送消息")
    public ResultUtil<Object> topicExchange(@Valid @ApiParam(name = "msg", value = "发送消息", required = true) @RequestParam("msg") String msg){
        sendRabbitMsgService.TestSendExchangeMsg(msg);
        return ResultUtil.success();
    }

    @GetMapping("/fanout")
    @ApiOperation(value = "测试扇形交换机发送消息")
    public ResultUtil<Object> fanoutExchange(@Valid @ApiParam(name = "msg", value = "发送消息", required = true) @RequestParam("msg") String msg){
        sendRabbitMsgService.SendFanoutExchangeMsg(msg);
        return ResultUtil.success();
    }


}
