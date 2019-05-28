package com.cp.interview.designPattern.templateMethod;

public class Client {
    public static void main(String[] args) {
        AbstractClass ac;
        ac = new ConcreteClass();
        ac.templateMethod();
    }
}
