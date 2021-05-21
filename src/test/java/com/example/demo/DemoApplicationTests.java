package com.example.demo;

import com.example.demo.domain.Users;
import com.example.demo.modules.test.controller.Index;
import com.example.demo.modules.test.service.CurdJpaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private CurdJpaService curdJpaService;

    @Test
    void contextLoads() {

        Users index = curdJpaService.index();
        log.info(index.toString());
    }

}
