package com.example.demo;

import com.example.demo.modules.test.controller.Index;
import com.example.demo.modules.test.service.CurdJpaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
@Autowired
private Index index;
    @Autowired
    private CurdJpaService curdJpaService;

    @Test
    void contextLoads() {

        curdJpaService.index();
    }

}
