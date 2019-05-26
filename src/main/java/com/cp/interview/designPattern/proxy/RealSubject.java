package com.cp.interview.designPattern.proxy;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("真实请求。。。");
    }
}
