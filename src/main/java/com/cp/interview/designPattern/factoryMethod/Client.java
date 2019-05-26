package com.cp.interview.designPattern.factoryMethod;

public class Client {
    public static void main(String[] args) {
        Factory factory;
        //可通过配置文件配置
        factory = new ConcreteFactoryA();

        Product product;
        product = factory.getProduct();

        product.methodSame();
    }
}
