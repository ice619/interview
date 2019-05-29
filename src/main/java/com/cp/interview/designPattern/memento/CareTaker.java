package com.cp.interview.designPattern.memento;

/**
 * 负责人类：保存备忘录对象
 */
public class CareTaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
