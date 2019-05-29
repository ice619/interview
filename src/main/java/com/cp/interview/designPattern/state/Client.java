package com.cp.interview.designPattern.state;

/**
 * 状态模式
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA(), 1);
        context.request();
    }
}
