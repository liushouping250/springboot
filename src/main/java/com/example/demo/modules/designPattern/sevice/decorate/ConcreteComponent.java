package com.example.demo.modules.designPattern.sevice.decorate;

/**
 * ConcreteComponent（具体被装饰对象）
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/7/10 16:09
 */
public class ConcreteComponent implements Component {
    @Override
    public void eat() {
        System.out.println("具体被装饰对象");
    }
}
