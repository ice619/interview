package com.cp.interview.designPattern.chainOfResponsibility;

/**
 * 职责链模式
 */
public class Client {
    public static void main(String[] args) {
        Handler handler = new ConcreteHandler();
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();

        //设置职责链顺序
        handler.setSuccessor(handler1);
        handler1.setSuccessor(handler2);

        handler.handleRequest(1);
        System.out.println("-----------------------");
        handler.handleRequest(10);
        System.out.println("-----------------------");
        handler.handleRequest(30);
        System.out.println("-----------------------");

    }
}
