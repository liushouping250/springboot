package com.example.demo.modules.user.service.impl;

import com.example.demo.config.security.jwt.JWTConfigurer;
import com.example.demo.config.security.jwt.TokenProvider;
import com.example.demo.domain.TeUsers;
import com.example.demo.mapper.TeUsersMapper;
import com.example.demo.modules.user.pojo.request.DeleteUserDTO;
import com.example.demo.modules.user.pojo.request.LoginUserDTO;
import com.example.demo.modules.user.pojo.request.UserRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.monster.liu
 * @since 2021-05-27
 */
@Service
@Slf4j
public class TeUsersServiceImpl{

    @Resource
    private TeUsersMapper teUsersMapper;

    @Autowired
    private TokenProvider tokenProvider;

    public Object queryUserList(){
        return teUsersMapper.queryList();
    }

    public TeUsers queryUserInfo(Integer userId){
        return  teUsersMapper.findUserByUserId(userId);
    }

    public TeUsers insertUser(UserRequestDTO userRequestDTO){
        TeUsers user = new TeUsers();
        user.setMobile(userRequestDTO.getMobile());
        user.setName(userRequestDTO.getName());
        user.setNickname(userRequestDTO.getNickname());
        user.setPassword(userRequestDTO.getPassword());
        if(userRequestDTO.getUserId() != null){
             teUsersMapper.updateUser(user);
        }else {
             teUsersMapper.insertUser(user);
        }
        return  user;
    }

    public int deleteUser(DeleteUserDTO deleteUserDTO){
        return  teUsersMapper.deleteUser(deleteUserDTO.getUserId());
    }

    /**
     *  用户登录
     *
     *
     * @return*/
    public TeUsers login(LoginUserDTO loginUserDTO, HttpServletResponse response) throws Exception {
        TeUsers userByUserAndPassword = teUsersMapper.findUserByUserAndPassword(loginUserDTO.getUsername(), loginUserDTO.getPassword());
        Optional.ofNullable(userByUserAndPassword).orElseThrow(()->new RuntimeException("登录失败"));

        String token = tokenProvider.createToken(userByUserAndPassword.getToken(), true);
        response.setHeader(JWTConfigurer.AUTHORIZATION_HEADER,"Bearer " + token + "@" + null + "@" + userByUserAndPassword.getToken());
        return userByUserAndPassword;

    }



}
