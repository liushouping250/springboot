package com.example.demo.modules.test.controller;

import com.example.demo.modules.test.dto.request.TestRequestDTO;
import com.example.demo.modules.test.dto.response.TestResponseDTO;
import com.example.demo.utils.ResultUtil;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "test", value = "关键字", required = true, paramType = "query", dataType = "String"),
    })
    public ResultUtil<Object> index(@Valid @RequestParam(required = true) String test){
        log.info(test);
        return ResultUtil.success(test);
    }


    @PostMapping("/test")
    @ApiOperation(value = "测试接口2",response = TestRequestDTO.class)
    public ResultUtil<Object> test(@RequestBody TestRequestDTO testRequestDTO){
        log.info(testRequestDTO.toString());
        TestResponseDTO testResponseDTO = new TestResponseDTO();
        testResponseDTO.setResult(testRequestDTO);
        return ResultUtil.success(testResponseDTO);
    }

}
