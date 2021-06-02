package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.TeArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.monster.liu
 * @since 2021-05-31
 */
@Mapper
public interface TeArticleMapper extends BaseMapper<TeArticle> {

    //批量增加文章信息
    int insetBatch(List<TeArticle> teArticleList);

    List<TeArticle> queryArticleList();
}
