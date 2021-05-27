package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.Users;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface TeUsersMapper extends BaseMapper<Users> {

    List<Users> queryList();

    @Select(" select * from te_users where user_id =#{userId} ")
    Users findUserByUserId(@Param("userId")Integer userId);

}
