package com.example.demo.modules.rabbitmq.service;

import cn.hutool.json.JSONUtil;
import com.example.demo.domain.TeUsers;
import com.example.demo.rabbitmq.RabbitMqRpcConfig;
import com.example.demo.util.RabbitRpcBuilderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * @description: 发送rpc业务类
 * @author: Mr.monster.liu
 * @create: 2021-06-07 14:06
 **/
@Service
@Slf4j
public class SendRabbitRpcMsgService {

    @Autowired
    private RabbitMqRpcConfig rabbitMqRpcConfig;

    public TeUsers index() throws IOException, InterruptedException {
        TeUsers teUsers = new TeUsers();
        teUsers.setPassword("111");
        teUsers.setNickname("aaaaa");
        String s = JSONUtil.toJsonStr(teUsers);
        RabbitRpcBuilderUtil.Header header = new RabbitRpcBuilderUtil.Header().setContentType("json").build();
        RabbitRpcBuilderUtil.Builder builder = new RabbitRpcBuilderUtil.Builder().setData(s).setHeader(header).build();

        RabbitRpcBuilderUtil build = new RabbitRpcBuilderUtil(builder).build();
        log.info(build.toString());
        byte[] bytes = RabbitRpcBuilderUtil.objectToBytes(build);
        String call = rabbitMqRpcConfig.call( "topic.tes2t",bytes,"2000",10000);
        TeUsers teUsers2 = new TeUsers();
        if(call != null){
            teUsers2 = JSONUtil.toBean(call, TeUsers.class);
        }
        return teUsers2;
    }


}
