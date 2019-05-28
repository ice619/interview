package com.cp.interview.designPattern.observer;

import com.cp.interview.designPattern.observer.ConcreteSubject;
import com.cp.interview.designPattern.observer.Observer;

public class ConcreteObserver implements Observer {
    private String name;
    private String observerState;
    private ConcreteSubject subject;

    public ConcreteObserver(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
    }

    @Override
    public void update() {
        observerState = subject.getSubjectState();
        System.out.println( name + "  的状态:"  + observerState);
    }
}
