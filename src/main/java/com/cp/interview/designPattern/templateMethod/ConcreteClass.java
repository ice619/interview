package com.cp.interview.designPattern.templateMethod;

public class ConcreteClass extends AbstractClass {

    @Override
    public void primitiveOperation2() {
        System.out.println("子类实现 primitiveOperation2");
    }
    @Override
    public boolean primitiveOperation3(){
        System.out.println("子类实现 primitiveOperation3");
        return true;
    }
}
