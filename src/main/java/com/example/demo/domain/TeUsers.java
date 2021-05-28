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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.monster.liu
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("te_users")
@ApiModel(value="TeUsers对象", description="")
@Entity
public class TeUsers implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @TableId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    private String mobile;

    @TableField("head_portrait")
    private String headPortrait;

    @ApiModelProperty(value = "密码")
    private String password;

    private String token;

    @ApiModelProperty(value = "积分")
    private Integer integral;

    @ApiModelProperty(value = "余额")
    private Float balance;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;


}
