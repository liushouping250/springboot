package com.example.demo.modules.designPattern.sevice.builderT;/** * @description: * @author: Mr.monster.liu * @create: 2021-07-09 17:27 **/public interface MustCalculationInterface {    //注入必要的类    <T> T setMustCalculation(MustCalculationBuilder mustCalculationBuilder);    //检测价格是否正常 购买价格大于成本    <T> T checkGoods();    //获取用户族谱    <T> T  findUserFamily();     MustCalculationBuilder builder();}