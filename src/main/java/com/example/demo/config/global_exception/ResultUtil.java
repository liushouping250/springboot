package com.example.demo.config.global_exception;import io.swagger.annotations.ApiModelProperty;import lombok.Data;/** * @description: 统一响应数据 * @author: Mr.monster.liu * @create: 2021-05-11 16:19 **/@Datapublic class ResultUtil {    @ApiModelProperty("响应代码")    private Integer  code;    @ApiModelProperty("响应信息")    private  String msg;    @ApiModelProperty("响应结果集")    private  Object result;    public static ResultUtil success(Object object) {        ResultUtil objectTestResponseDTO = new ResultUtil();        objectTestResponseDTO.setCode(ResultCode.SUCCESS.getCode());        objectTestResponseDTO.setMsg(ResultCode.SUCCESS.getMsg());        objectTestResponseDTO.setResult(object);        return objectTestResponseDTO;    }    public static ResultUtil success() {        return success(null);    }    public static ResultUtil error(Integer code, String msg) {        ResultUtil objectTestResponseDTO = new ResultUtil();        objectTestResponseDTO.setCode(code);        objectTestResponseDTO.setMsg(msg);        objectTestResponseDTO.setResult(new Object());        return objectTestResponseDTO;    }}