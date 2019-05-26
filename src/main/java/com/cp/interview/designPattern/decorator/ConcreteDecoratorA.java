package com.cp.interview.designPattern.decorator;

public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    public void operation(){
        //调用原有业务方法
        super.operation();

        //调用新增业务方法
        addedBehavior();
    }

    public void addedBehavior(){
        System.out.println("ConcreteDecoratorA 新加业务方法");
    }

}
