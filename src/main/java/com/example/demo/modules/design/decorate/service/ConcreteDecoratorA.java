package com.example.demo.modules.design.decorate.service;

import com.example.demo.modules.design.decorate.Component;
import com.example.demo.modules.design.decorate.Decorator;

/**
 * ConcreteDecorator（具体装饰者）
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/7/10 16:12
 */
public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component){
        super(component);
    }

    @Override
    public void eat() {
        super.eat();
        reEat();
        System.out.println("ConcreteDecoratorA类");
    }

    public void reEat() {
        System.out.println("其他操作");
    }

}
