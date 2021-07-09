package com.example.demo.modules.designPattern.sevice.builderT.service;import com.example.demo.modules.designPattern.sevice.builderT.MustCalculation;import com.example.demo.modules.designPattern.sevice.builderT.MustCalculationBuilder;import com.example.demo.modules.designPattern.sevice.builderT.MustCalculationInterface;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Component;import java.util.Map;/** * @description: 服务类 * @author: Mr.monster.liu * @create: 2021-07-09 16:35 **/@Component@Slf4jpublic class CalculationService {    @Autowired    private Map<String, MustCalculationInterface> mustCalculationInterfaceMap;    public void index(){         MustCalculationBuilder mustCalculationBuilder = new MustCalculationBuilder()                .setCommission(40.00)                .setCost(20.00)                .setPrice(100.00)                .setUserId("1");        MustCalculationInterface mustCalculationSecond = mustCalculationInterfaceMap.get("mustCalculation");        mustCalculationSecond.setMustCalculation(mustCalculationBuilder);        mustCalculationSecond.checkGoods();        MustCalculationBuilder builder = mustCalculationSecond.builder();        log.info(builder.toString());    }}