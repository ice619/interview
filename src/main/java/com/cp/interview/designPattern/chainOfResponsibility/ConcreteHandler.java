package com.cp.interview.designPattern.chainOfResponsibility;

public class ConcreteHandler extends Handler {
    @Override
    public void handleRequest(int request) {
        if(request >= 0 && request <10){
            System.out.println("ConcreteHandler 处理请求");
        }else {
            this.successor.handleRequest(request);
        }
    }
}
