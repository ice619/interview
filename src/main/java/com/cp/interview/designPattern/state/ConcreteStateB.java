package com.cp.interview.designPattern.state;

public class ConcreteStateB extends State {
    @Override
    public void handle(Context context) {
        System.out.println(context.getState() + "===" + context.getValue());
        context.setState(new ConcreteStateA());
    }
}
