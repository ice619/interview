package com.cp.interview.designPattern.mediator;

public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String message) {
        mediator.send(message, this);
    }

    public void notify(String message){
        System.out.println("ConcreteColleague2 得到信息：" + message);
    }
}
