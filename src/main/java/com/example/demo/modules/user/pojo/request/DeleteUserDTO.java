package com.example.demo.modules.user.pojo.request;import io.swagger.annotations.ApiModelProperty;import lombok.Data;import javax.validation.constraints.NotNull;/** * @description: 注销用户 * @author: Mr.monster.liu * @create: 2021-05-28 15:28 **/@Datapublic class DeleteUserDTO {    @ApiModelProperty("用户ID")    @NotNull(message = "用户ID必填")    private Integer userId;}