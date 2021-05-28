package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.TeUsers;
import org.apache.ibatis.annotations.*;

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
public interface TeUsersMapper extends BaseMapper<TeUsers> {

    List<TeUsers> queryList();

    @Select(" select * from te_users where user_id =#{userId} ")
    TeUsers findUserByUserId(@Param("userId")Integer userId);

    @Insert(" INSERT  INTO  te_users ( user_id, name, nickname, mobile, password, token, integral, balance, create_time) VALUES (#{userId}, #{name}, #{nickname}, #{mobile}, #{password}, #{token}, #{integral}, #{balance}, #{createTime}) ")
    int insertUser(TeUsers user);

    @Update("update user set name =#{name},nickname =#{nickname},mobile =#{mobile},password =#{password} where id=#{userId}")
    TeUsers updateUser(TeUsers user);

}
