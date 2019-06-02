package com.cp.interview.designPattern.chainOfResponsibility;

public class ConcreteHandler2 extends Handler {
    @Override
    public void handleRequest(int request) {
        if(request >= 20){
            System.out.println("ConcreteHandler2 处理请求");
        }
    }
}
