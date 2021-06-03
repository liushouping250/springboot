package com.example.demo.modules.user.controller;


import com.example.demo.config.global_exception.ResultUtil;
import com.example.demo.config.security.jwt.HeaderMapRequestWrapper;
import com.example.demo.domain.TeUsers;
import com.example.demo.modules.user.pojo.request.DeleteUserDTO;
import com.example.demo.modules.user.pojo.request.LoginUserDTO;
import com.example.demo.modules.user.pojo.request.UserRequestDTO;
import com.example.demo.modules.user.pojo.response.UserResponseVO;
import com.example.demo.modules.user.service.impl.TeUsersServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Slf4j
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


    @PostMapping("/login")
    @ApiOperation(value = "用户登录",response = TeUsers.class)
    public  ResultUtil<Object> login(@Valid @RequestBody LoginUserDTO loginUserDTO, HttpServletResponse response) throws Exception {
        return  ResultUtil.success(teUsersService.login(loginUserDTO,response));
    }



}

