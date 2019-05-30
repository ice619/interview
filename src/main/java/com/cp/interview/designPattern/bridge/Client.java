package com.cp.interview.designPattern.bridge;

/**
 * 桥接模式
 */
public class Client {
    public static void main(String[] args) {
        Abstraction abstraction = new RefinedAbstraction(new ConcreteImplementorA());
        abstraction.operation();
    }
}
