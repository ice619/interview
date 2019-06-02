package com.cp.interview.designPattern.chainOfResponsibility;

public class ConcreteHandler1 extends Handler {
    @Override
    public void handleRequest(int request) {
        if(request >= 10 && request <20){
            System.out.println("ConcreteHandler1 处理请求");
        }else {
            this.successor.handleRequest(request);
        }
    }
}
