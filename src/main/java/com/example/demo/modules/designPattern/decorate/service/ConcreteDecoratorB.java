package com.example.demo.modules.designPattern.decorate.service;

import com.example.demo.modules.designPattern.decorate.Component;
import com.example.demo.modules.designPattern.decorate.Decorator;

/**
 * ConcreteDecorator（具体装饰者）
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/7/10 16:12
 */
public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    public void eat() {
        super.eat();
        System.out.println("===============");
        System.out.println("ManDecoratorB类");
    }

}
