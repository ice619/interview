package com.cp.interview.designPattern.abstractFactory;

public class CompanyOneFactory implements Factory{
    @Override
    public ProductA createProductA() {
        return new CompanyOneProductA();
    }

    @Override
    public ProductB createProductB() {
        return new CompanyOneProductB();
    }

    @Override
    public ProductC createProductC() {
        return new CompanyOneProductC();
    }
}
