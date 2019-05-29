package com.cp.interview.designPattern.abstractFactory;

public class CompanyTwoFactory implements Factory {
    @Override
    public ProductA createProductA() {
        return new CompanyTwoProductA();
    }

    @Override
    public ProductB createProductB() {
        return new CompanyTwoProductB();
    }

    @Override
    public ProductC createProductC() {
        return new CompanyTwoProductC();
    }
}
