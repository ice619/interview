package com.cp.interview.designPattern.memento;

/**
 * 备忘录
 */
public class Memento {
    private String state;

    public Memento(Originator o) {
        this.state = o.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
