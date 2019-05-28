package com.cp.interview.designPattern.builder;

public abstract class Builder {
    protected Product product = new Product();

    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();

    public abstract Product getResult();
}
