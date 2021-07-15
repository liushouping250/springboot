package com.example.demo.modules.design.decorate;

/**
 * Decorator（装饰者抽象类）
 * @author mr.monster
 * @version 1.0
 * @Description
 * @date 2021/7/10 16:10
 */
public abstract class Decorator implements Component {

    protected Component component;

    public  Decorator(Component component){
        this.component = component;
    }


    @Override
    public void eat() {
        component.eat();
    }

}
