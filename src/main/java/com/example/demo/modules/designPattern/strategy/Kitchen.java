package com.example.demo.modules.designPattern.strategy;/** * @description: 厨房类 * @author: Mr.monster.liu * @create: 2021-06-16 16:12 **/public class Kitchen {    private CrabCooking strategy;    //抽象策略    public void setStrategy(CrabCooking strategy) {        this.strategy = strategy;    }    public CrabCooking getStrategy() {        return strategy;    }    public void CookingMethod() {        strategy.CookingMethod();    //做菜    }}