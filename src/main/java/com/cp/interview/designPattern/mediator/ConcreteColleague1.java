package com.cp.interview.designPattern.mediator;

public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String message) {
        mediator.send(message, this);
    }

    public void notify(String message){
        System.out.println("ConcreteColleague1 得到信息：" + message);
    }
}
