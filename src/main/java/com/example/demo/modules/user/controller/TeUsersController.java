package com.example.demo.modules.user.controller;


import com.example.demo.config.global_exception.ResultUtil;
import com.example.demo.modules.user.service.impl.TeUsersServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr.monster.liu
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/api/user")
public class TeUsersController {

    @Autowired
    private TeUsersServiceImpl teUsersService;

    @GetMapping("/list")
    @ApiOperation(value = "测试接口2")
    public ResultUtil<Object> queryUsersList(){
      return   ResultUtil.success(teUsersService.queryUserList());
    }

    @GetMapping("/info")
    @ApiOperation(value = "测试接口2")
    public ResultUtil<Object> queryUsersInfo(@Valid @ApiParam("userId") Integer userId){
        return   ResultUtil.success(teUsersService.queryUserInfo(userId));
    }


}

