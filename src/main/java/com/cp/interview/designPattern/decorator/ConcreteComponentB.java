package com.cp.interview.designPattern.decorator;

public class ConcreteComponentB implements Component {
    @Override
    public void operation() {
        System.out.println("ConcreteComponentB 原有业务方法");
    }
}
