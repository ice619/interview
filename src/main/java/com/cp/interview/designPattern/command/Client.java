package com.cp.interview.designPattern.command;

/**
 * 命令模式
 */
public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker(new ConcreteCommand());

        invoker.call();
    }
}
