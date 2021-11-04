package com.example.demo.config.global_exception;

/**
 * @description: 统一返回定义
 * @author: Mr.monster.liu
 * @create: 2021-05-15 16:18
 **/

public enum ResultCode {

    SUCCESS(200,"操作成功"),
    ERROR_EXCEPTION(201,"系统内部异常"),
    ERROR_NOT_POINT(202,"空指针异常"),
    ERROR_NOT_VALID(203,"参数校验失败"),
    PARAMETER_FORMAT(204,"参数请求异常，请用JSON格式"),
    SQL_EXCEPTION(205,"数据库异常"),
    MYBATIS_EXCEPTION(206,"MYBATIS异常"),
    RUNTIME_EXCEPTION(207,"运行异常"),
    HANDLER_EXCEPTION(208,"验证异常");
    private Integer  code;

    private  String msg;

    ResultCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode(){
        return  this.code;
    }


    public  String getMsg(){
        return  this.msg;
    }

}
