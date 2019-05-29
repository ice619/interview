package com.cp.interview.designPattern.memento;

import lombok.Data;

/**
 * 发起人
 */
@Data
public class Originator {
    private String state;

    public Memento createMemento(){
        return new Memento(this);
    }
    public void restoreMemento(Memento m){
        state = m.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void show(){
        System.out.println("state:" + state);
    }
}
