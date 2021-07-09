package com.example.demo.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("te_goods")
@ApiModel(value="TeGoods对象", description="")
public class TeGoods implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "goods_Id", type = IdType.AUTO)
    private Integer goodsId;

    @ApiModelProperty(value = "商品名称")
    @TableField("goods_name")
    private String goodsName;

    @ApiModelProperty(value = "商品类型ID")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty(value = "商品标题")
    @TableField("goods_title")
    private String goodsTitle;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存数量")
    private Integer stock;

    @ApiModelProperty(value = "真实销售量")
    @TableField("real_sales_volume")
    private Integer realSalesVolume;

    @ApiModelProperty(value = "虚假销售量")
    @TableField("false_sales_volume")
    private Integer falseSalesVolume;

    @ApiModelProperty(value = "商品状态 0 编辑中  1 发布 2 审核中 3 已审核  4 审核未通过 5 下架")
    private Boolean status;


}
