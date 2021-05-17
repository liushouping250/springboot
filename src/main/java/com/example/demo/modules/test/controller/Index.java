package com.example.demo.modules.test.controller;

import com.example.demo.modules.test.dto.request.TestRequestDTO;
import com.example.demo.config.global_exception.ResultUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/index")
    @ApiOperation("测试接口1")
    public ResultUtil index(@ApiParam(name = "test", value = "订单id", required = true) @RequestParam("test") String test){
        log.info(test);
        return ResultUtil.success(test);
    }


    @PostMapping("/test")
    @ApiOperation(value = "测试接口2",response = TestRequestDTO.class)
    public ResultUtil test(@RequestBody @Valid TestRequestDTO testRequestDTO){
        log.info(testRequestDTO.toString());
         Integer num = testRequestDTO.getNum();
         Integer i = 2;
         if( i > num){
             testRequestDTO.setNum(12);
         }
        return ResultUtil.success(testRequestDTO);
    }

}
