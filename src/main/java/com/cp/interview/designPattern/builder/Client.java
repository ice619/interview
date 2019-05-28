package com.cp.interview.designPattern.builder;

/**
 * 建造者模式
 */
public class Client {
    public static void main(String[] args) {
        Director director1 = new Director(new ConcreteBuilder1());
        Product product1 = director1.construct();
        System.out.println(product1);

        Director director2 = new Director(new ConcreteBuilder2());
        Product product2 = director2.construct();
        System.out.println(product2);
    }
}
