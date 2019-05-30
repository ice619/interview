package com.cp.interview.designPattern.bridge;

public class ConcreteImplementorA implements Implementor {
    @Override
    public void operation() {
        System.out.println("A方法");
    }
}
