package com.example.demo;

import com.example.demo.domain.TeUsers;
import com.example.demo.modules.redis.service.RedisBasicService;
import com.example.demo.modules.test.service.CurdJpaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private CurdJpaService curdJpaService;

    @Autowired
    private RedisBasicService redisBasicService;

    @Test
    void contextLoads() {
        TeUsers index = curdJpaService.index();
        log.info(index.toString());
    }





    void TestStream(){
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Object[] users = new Object[10];
        //2.通过Arrays中的静态方法stream() 获取数组流
        Stream<Object> stream2 = Arrays.stream(users);

        //3.通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        //4.创建无限流
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream3.forEach(System.out::println);
        stream4.limit(10).forEach(x -> System.out.println(x));


    }

    @Test
    void  testRedis(){
        redisBasicService.RedisSet();
    }

}
