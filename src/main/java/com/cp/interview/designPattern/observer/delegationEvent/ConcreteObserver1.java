package com.cp.interview.designPattern.observer.delegationEvent;

import com.cp.interview.designPattern.observer.delegationEvent.ConcreteSubject;

public class ConcreteObserver1 {

    private String name;
    private String observerState;
    private ConcreteSubject subject;

    public ConcreteObserver1(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
    }

    public void update1() {
        observerState = subject.getSubjectState();
        System.out.println( name + "  的状态:"  + observerState);
    }
}
