package com.example.demo.modules.designPattern.sevice.builderT;import org.springframework.stereotype.Component;/** * @description: * @author: Mr.monster.liu * @create: 2021-07-09 18:00 **/@Componentpublic class MustCalculationSecond implements MustCalculationInterface {    //需要构建注入的类    private MustCalculationBuilder mustCalculationBuilder;    @Override    public MustCalculationSecond setMustCalculation(MustCalculationBuilder mustCalculationBuilder) {        this.mustCalculationBuilder = mustCalculationBuilder;        return this;    }    @Override    public MustCalculationSecond checkGoods() {        return this;    }    @Override    public MustCalculationSecond findUserFamily() {        return this;    }    @Override    public MustCalculationBuilder builder() {        return this.mustCalculationBuilder;    }}