package com.example.demo.modules.designPattern.sevice.strategymodel;/** * @description: 具体策略类：清蒸大闸蟹 * @author: Mr.monster.liu * @create: 2021-06-16 16:14 **/public class SteamedCrabs   implements CrabCooking {    private static final long serialVersionUID = 1L;    @Override    public void CookingMethod() {        System.out.println("清蒸大闸蟹");    }}