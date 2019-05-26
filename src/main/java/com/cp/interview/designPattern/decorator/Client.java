package com.cp.interview.designPattern.decorator;

/**
 * 装饰者模式
 */
public class Client {
    public static void main(String[] args) {
        Component component, concreteDecoratorA, concreteDecoratorB;
        component = new ConcreteComponentA();

        //用concreteDecoratorA 包装 component
        concreteDecoratorA  = new ConcreteDecoratorA(component);
        concreteDecoratorA.operation();

        //用concreteDecoratorB 包装 concreteDecoratorA
        concreteDecoratorB  = new ConcreteDecoratorB(concreteDecoratorA);
        concreteDecoratorB.operation();
    }
}
