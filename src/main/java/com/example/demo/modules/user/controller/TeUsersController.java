package com.example.demo.modules.user.controller;


import com.example.demo.config.global_exception.ResultUtil;
import com.example.demo.modules.user.pojo.request.DeleteUserDTO;
import com.example.demo.modules.user.pojo.request.UserRequestDTO;
import com.example.demo.modules.user.pojo.response.UserResponseVO;
import com.example.demo.modules.user.service.impl.TeUsersServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "用户相关")
public class TeUsersController {

    @Autowired
    private TeUsersServiceImpl teUsersService;

    @GetMapping("/list")
    @ApiOperation(value = "用户列表")
    public ResultUtil<Object> queryUsersList(){
      return   ResultUtil.success(teUsersService.queryUserList());
    }

    @GetMapping("/info")
    @ApiOperation(value = "用户信息")
    public ResultUtil<Object> queryUsersInfo(@Valid @ApiParam(name = "userId", value = "用户id", required = true) Integer userId){
        return   ResultUtil.success(teUsersService.queryUserInfo(userId));
    }

    @PostMapping("/insert")
    @ApiOperation(value = "用户注册",response = UserResponseVO.class)
    public  ResultUtil<Object> insertUser( @Valid @RequestBody UserRequestDTO userRequestDTO){
        return  ResultUtil.success(teUsersService.insertUser(userRequestDTO));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "用户注销")
    public  ResultUtil<Object> deleteUser(@Valid @RequestBody DeleteUserDTO deleteUserDTO){
        return  ResultUtil.success(teUsersService.deleteUser(deleteUserDTO));
    }



}

