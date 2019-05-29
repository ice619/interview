package com.cp.interview.designPattern.state;

public class ConcreteStateA extends State {
    @Override
    public void handle(Context context) {
        context.setValue(context.getValue() + context.getValue());
        context.setState(new ConcreteStateB());
    }
}
