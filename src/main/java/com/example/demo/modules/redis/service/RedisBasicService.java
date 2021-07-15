package com.example.demo.modules.redis.service;

import com.example.demo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * @description: redis 实现类
 * @author: Mr.monster.liu
 * @create: 2021-06-10 18:37
 **/
@Service
@Slf4j
public class RedisBasicService {

    @Autowired
    private RedisUtil redisUtil;


    public boolean RedisSet(){
        String str = "sssss";
        return redisUtil.set("key",str,10000);
    }


    public boolean RedisLock() throws InterruptedException {
        for (int i=0;i<100;i++){
            play();
        }
        return true;
    }
    @Async    // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
    public void play() throws InterruptedException {
        System.out.println("f1 : " + Thread.currentThread().getName() + "   " + UUID.randomUUID().toString());
        boolean key = redisUtil.getLock("lock", 100);
        if(key){
            log.info("获取锁成功===="+Thread.currentThread().getName());
            redisUtil.releaseLock("lock");
        }else {
            log.info("获取锁失败===="+Thread.currentThread().getName());
        }

    }





}
