package com.example.demo.mapper;

import com.example.demo.domain.TeUsers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.monster.liu
 * @since 2021-05-27
 */
@Mapper
public interface TeUsersMapper{

    List<Users> queryList();

}
