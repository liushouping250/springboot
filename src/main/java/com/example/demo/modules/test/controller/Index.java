package com.example.demo.modules.test.controller;


import com.example.demo.modules.test.service.CurdJpaService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.modules.test.dto.request.TestRequestDTO;
import com.example.demo.config.global_exception.ResultUtil;

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

    @Autowired
    private CurdJpaService curdJpaService;


    @GetMapping("/index")
    @ApiOperation("测试接口1")
    public ResultUtil index(@Valid @ApiParam(name = "test", value = "订单id", required = true) @RequestParam("test") String test){
        return ResultUtil.success(curdJpaService.index());
    }


    @PostMapping("/test")
    @ApiOperation(value = "测试接口2",response = TestRequestDTO.class)
    public ResultUtil test(@RequestBody @Valid TestRequestDTO testRequestDTO){
        log.info(testRequestDTO.toString());
        return ResultUtil.success(testRequestDTO);
    }

}
