package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.monster.liu
 * @since 2021-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("te_category_goods")
@ApiModel(value="TeCategoryGoods对象", description="")
public class TeCategoryGoods implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty(value = "类别名称")
    @TableField("category_name")
    private String categoryName;

    @ApiModelProperty(value = "状态 0 编辑 1 正常 2待审核 3 审核失败 4 已关闭 ")
    private Boolean status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @TableField("update_time")
    private Date updateTime;

    @TableField("create_time")
    private Date createTime;


}
