package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.monster.liu
 * @since 2021-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("te_article")
@ApiModel(value="TeArticle对象", description="")
public class TeArticle implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "发布人")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "类别")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "小标题")
    @TableField("small_title")
    private String smallTitle;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "点击量")
    @TableField("click_num")
    private Integer clickNum;

    @ApiModelProperty(value = "点赞量")
    @TableField("likes_num")
    private Integer likesNum;

    @TableField("update_time")
    private Date updateTime;

    @TableField("create_time")
    private Date createTime;


}
