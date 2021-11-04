package com.example.demo.config.global_exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import java.net.ConnectException;
import java.sql.SQLException;

/**
 * @description: 全局统一处理异常
 * @author: Mr.monster.liu
 * @create: 2021-05-11 16:26
 **/
//注解定义全局异常处理类
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);




    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
   /* @ExceptionHandler(BusinessException.class)
    @ResponseBody
    AppResponse handleBusinessException(BusinessException e){
        LOGGER.error(e.getMessage(), e);

        AppResponse response = new AppResponse();
        response.setFail(e.getMessage());
        return response;
    }*/

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultUtil<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.info("数据验证异常:",e);
        return ResultUtil.error(ResultCode.ERROR_NOT_VALID.getCode(),e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }


    /**
     * 处理空指针的异常
     * @param
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ResultUtil<Object> handleException(NullPointerException e){
        log.info("空指针异常:",e);
        return ResultUtil.error(ResultCode.ERROR_NOT_POINT.getCode(),ResultCode.ERROR_NOT_POINT.getMsg());
    }


    // @ExceptionHandler 注解声明异常处理方法
    /*@ExceptionHandler()
    @ResponseBody
    public ResultUtil handleException(Exception e){
        log.info("系统异常:" + e.getMessage());
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(ResultCode.ERROR_EXCEPTION.getCode());
        resultUtil.setMsg(ResultCode.ERROR_EXCEPTION.getMsg());
        return resultUtil;
    }*/

    @ExceptionHandler()
    @ResponseBody
    public ResultUtil<Object> HttpMessageNotReadableException(HttpMessageNotReadableException e){
        log.info("json解析异常:" + e.getMessage());
        return ResultUtil.error(ResultCode.PARAMETER_FORMAT.getCode(),ResultCode.PARAMETER_FORMAT.getMsg());
    }


    @ExceptionHandler()
    @ResponseBody
    public ResultUtil<Object> HttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        log.info("json解析异常:" + e.getMessage());
        return ResultUtil.error(ResultCode.PARAMETER_FORMAT.getCode(),ResultCode.PARAMETER_FORMAT.getMsg());
    }

    @ExceptionHandler()
    @ResponseBody
    public ResultUtil<Object> ConnectException(ConnectException e){
        log.info("连接异常:" + e.getMessage());
        return ResultUtil.error(ResultCode.PARAMETER_FORMAT.getCode(),ResultCode.PARAMETER_FORMAT.getMsg());
    }

    @ExceptionHandler()
    @ResponseBody
    public ResultUtil<Object> SQLException(SQLException e){
        log.info("数据库异常：:" + e.getMessage());
        return ResultUtil.error(ResultCode.SQL_EXCEPTION.getCode(),e.getMessage());
    }

    @ExceptionHandler()
    @ResponseBody
    public ResultUtil<Object> RuntimeException(RuntimeException e){
        log.info("业务异常:" + e.getMessage());
        return ResultUtil.error(ResultCode.RUNTIME_EXCEPTION.getCode(),e.getMessage());
    }


    @ExceptionHandler()
    @ResponseBody
    public ResultUtil<Object> NestedServletException(NestedServletException e){
        log.info("mybatis操作异常:" + e.getMessage());
        return ResultUtil.error(ResultCode.MYBATIS_EXCEPTION.getCode(),e.getMessage());
    }






}
