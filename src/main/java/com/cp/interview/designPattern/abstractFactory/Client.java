package com.cp.interview.designPattern.abstractFactory;

/**
 * 抽象工厂模式
 */
public class Client {
    public static void main(String[] args) {
        Factory factory;
        //这里可以通过配置文件 反射获取
        factory = new CompanyTwoFactory();
        ProductA productA = factory.createProductA();
        ProductB productB = factory.createProductB();
        ProductC productC = factory.createProductC();

        productA.display();
        productB.display();
        productC.display();

    }
}
