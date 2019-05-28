package com.cp.interview.designPattern.builder;

public class ConcreteBuilder1 extends Builder {
    private Product product = new Product();

    @Override
    public Product getResult() {
        return product;
    }

    @Override
    public void buildPartA() {
        product.setPartA("部件1");
    }

    @Override
    public void buildPartB() {
        product.setPartB("部件2");
    }

    @Override
    public void buildPartC() {
        product.setPartC("部件3");
    }

}
