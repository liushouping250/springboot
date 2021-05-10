package com.example.demo.modules.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/5/9 15:10
 */
@RestController
@RequestMapping("/api/test")
@Api(tags = "测试")
public class Index {

    @GetMapping("/getAllCityCode")
    @ApiOperation("获取所有的站点城市")
    public  void  index(@ApiParam(name = "test",value = "测试",required = true) String Test){

    }

}
