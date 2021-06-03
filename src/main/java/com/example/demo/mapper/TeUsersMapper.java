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

    @Update("update te_users set name =#{name},head_portrait =#{headPortrait},nickname =#{nickname},mobile =#{mobile},password =#{password} where user_id=#{userId}")
    TeUsers updateUser(TeUsers user);

    @Delete(" delete from te_users where user_id = #{userId} ")
    int deleteUser(@Param("userId")Integer userId);

    @Select(" select * from te_users where name = #{username} and password = #{password} ")
    TeUsers findUserByUserAndPassword(@Param("username")String userName,@Param("password")String password);

}
