package com.example.demo.modules.test.controller;

import com.example.demo.domain.Users;
import com.example.demo.modules.test.service.CurdJpaService;
import com.example.demo.repository.TeUsersRepository;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/9 15:10
 */
@RestController
@RequestMapping("/api/test")
@Api(tags = "测试")
@Slf4j
public class Index {

    @Autowired
    private CurdJpaService curdJpaService;


    @GetMapping("/getAllCityCode")
    @ApiOperation("获取所有的站点城市")
    public  void  index(@ApiParam(name = "test",value = "测试",required = true) String Test){
        curdJpaService.index();
        return  ;
    }

}
