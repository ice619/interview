package com.cp.interview.designPattern.factoryMethod;

public class ConcreteFactoryA implements Factory{
    @Override
    public Product getProduct() {
        return new ProdectA();
    }
}
