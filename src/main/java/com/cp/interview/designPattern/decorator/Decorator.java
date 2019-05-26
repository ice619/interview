package com.cp.interview.designPattern.decorator;

/**
 * 抽象装饰类
 * 对于Component来说，无需知道Decorator的存在
 */
public abstract class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        //调用原有业务方法
        component.operation();
    }
}
