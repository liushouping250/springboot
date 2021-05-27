package com.example.demo.modules.user.service.impl;

import com.example.demo.domain.Users;
import com.example.demo.mapper.TeUsersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public List<Users> queryUserList(){
        List<Users> users = teUsersMapper.queryList();
        return users;
    }


}
