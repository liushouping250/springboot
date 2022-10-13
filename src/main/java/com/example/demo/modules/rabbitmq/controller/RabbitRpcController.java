//package com.example.demo.modules.rabbitmq.controller;
//
//import com.example.demo.config.global_exception.ResultUtil;
//import com.example.demo.modules.rabbitmq.service.SendRabbitRpcMsgService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
///**
// * @description: rabbit rpc测试
// * @author: Mr.monster.liu
// * @create: 2021-06-07 14:03
// **/
//@RestController
//@RequestMapping("api/rabbit/rpc")
//@Api(tags = "rpc测试")
//public class RabbitRpcController {
//
//    @Autowired
//    private SendRabbitRpcMsgService sendRabbitRpcMsgService;
//
//
//    @GetMapping("/index")
//    @ApiOperation(value = "测试RPC发送消息")
//    public ResultUtil<Object> index() throws IOException, InterruptedException {
//        return  ResultUtil.success(sendRabbitRpcMsgService.index());
//    }
//
//
//
//}
