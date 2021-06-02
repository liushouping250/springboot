package com.example.demo.modules.user.service.impl;

import com.example.demo.domain.TeUsers;
import com.example.demo.mapper.TeUsersMapper;
import com.example.demo.modules.user.pojo.request.DeleteUserDTO;
import com.example.demo.modules.user.pojo.request.UserRequestDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.monster.liu
 * @since 2021-05-27
 */
@Service
public class TeUsersServiceImpl{

    @Resource
    private TeUsersMapper teUsersMapper;

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



}
