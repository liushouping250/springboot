package com.example.demo.modules.entrust.service.impl;import com.example.demo.domain.TeGoods;import com.example.demo.domain.TeUsers;import com.example.demo.modules.entrust.service.EntrustInterface;import org.springframework.stereotype.Component;/** * @description: 普通会员计算价格 * @author: Mr.monster.liu * @create: 2021-06-28 10:32 **/public class OrdinaryMembersEntrustServiceImpl implements EntrustInterface {    @Override    public void setUser(TeUsers user) {    }    @Override    public void setGoods(TeGoods goods) {    }    @Override    public double calculateThePrice() {        return 0;    }}