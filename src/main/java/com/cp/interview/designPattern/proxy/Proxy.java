package com.cp.interview.designPattern.proxy;

public class Proxy implements Subject {
    private RealSubject realSubject = new RealSubject();
    public void preRequest(){
        System.out.println("代理，请求前，");
    }

    @Override
    public void request() {
        preRequest();
        realSubject.request();
        postRequest();
    }

    public void postRequest(){
        System.out.println("代理，请求后，，，");
    }
}
