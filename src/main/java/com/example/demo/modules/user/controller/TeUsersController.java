package com.example.demo.modules.user.controller;


import com.example.demo.config.global_exception.ResultUtil;
import com.example.demo.modules.user.service.impl.TeUsersServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr.monster.liu
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/modules/te-users")
public class TeUsersController {

    @Autowired
    private TeUsersServiceImpl teUsersService;

    @PostMapping("/test")
    @ApiOperation(value = "测试接口2")
    public ResultUtil queryUsersList(){
        ResultUtil.success(teUsersService.queryUserList());
    }


}

