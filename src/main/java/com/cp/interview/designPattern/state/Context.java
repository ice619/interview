package com.cp.interview.designPattern.state;

public class Context {
    /**
     * 维持一个对抽象状态对象的引用
     */
    private State state;

    /**
     * /其他属性值，该属性值的变化可能会导致对象状态发生变化
     */
    private int value;

    public Context(State state, int value) {
        this.state = state;
        this.value = value;
    }

    public void request(){
        //其他业务代码
        System.out.println(this.getValue());
        System.out.println(this.getState());

        System.out.println("-----------------");
        //调用状态对象的业务方法
        state.handle(this);

        System.out.println(this.getValue());
        System.out.println(this.getState());

    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
