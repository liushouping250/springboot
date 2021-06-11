package com.example.demo.modules.redis.controller;
import com.example.demo.modules.redis.service.RedisBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试redis使用
 * @author: Mr.monster.liu
 * @create: 2021-06-10 18:35
 **/
@RestController
@RequestMapping("api/redis")
@Api(tags = "redis测试")
public class RedisTestController {

    @Autowired
    private RedisBasicService redisBasicService;

    @GetMapping("/index")
    @ApiOperation(value = "测试RPC发送消息")
    public void index() {
        redisBasicService.RedisSet();
    }

    @GetMapping("/lock")
    @ApiOperation(value = "测试抢锁")
    public void lock() throws InterruptedException {
        redisBasicService.RedisLock();
    }
}

