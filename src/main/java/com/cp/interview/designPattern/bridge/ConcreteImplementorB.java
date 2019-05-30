package com.cp.interview.designPattern.bridge;

public class ConcreteImplementorB implements Implementor {
    @Override
    public void operation() {
        System.out.println("B 方法");
    }
}
