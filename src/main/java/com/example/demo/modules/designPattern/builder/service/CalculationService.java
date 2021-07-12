package com.example.demo.modules.designPattern.builder.service;import com.example.demo.modules.designPattern.builder.MustCalculation;import com.example.demo.modules.designPattern.builder.MustCalculationBuilder;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Component;/** * @description: 服务类 * @author: Mr.monster.liu * @create: 2021-07-09 16:35 **/@Component@Slf4jpublic class CalculationService {    @Autowired    private MustCalculation mustCalculation;    public void index(){         MustCalculationBuilder mustCalculationBuilder = new MustCalculationBuilder()                .setCommission(40.00)                .setCost(20.00)                .setPrice(100.00)                .setUserId("1");        MustCalculationBuilder builder = mustCalculation                .setMustCalculation(mustCalculationBuilder)                .checkGoods()                .findUserFamily()                .builder();        log.info(builder.toString());    }}