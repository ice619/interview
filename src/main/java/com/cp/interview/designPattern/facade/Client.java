package com.cp.interview.designPattern.facade;

/**
 * 外观模式（门面模式）
 */
public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.method();
    }
}
