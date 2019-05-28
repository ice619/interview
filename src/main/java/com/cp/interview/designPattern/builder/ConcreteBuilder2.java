package com.cp.interview.designPattern.builder;

public class ConcreteBuilder2 extends Builder {
    private Product product = new Product();

    @Override
    public Product getResult() {
        return product;
    }

    @Override
    public void buildPartA() {
        product.setPartA("部件4");
    }

    @Override
    public void buildPartB() {
        product.setPartB("部件5");
    }

    @Override
    public void buildPartC() {
        product.setPartC("部件6");
    }

}
