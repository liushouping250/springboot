package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.TeCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.monster.liu
 * @since 2021-05-31
 */
@Mapper
public interface TeCategoryMapper extends BaseMapper<TeCategory> {

    int insertCategory(TeCategory categoryInfo);


}
