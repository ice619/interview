package com.cp.interview.designPattern.decorator;

public class ConcreteComponentA implements Component {
    @Override
    public void operation() {
        System.out.println("ConcreteComponentA 原有业务方法");
    }
}
