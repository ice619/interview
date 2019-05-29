package com.cp.interview.designPattern.observer.delegationEvent;

import com.cp.interview.designPattern.observer.delegationEvent.ConcreteSubject;

public class ConcreteObserver2 {
    private String name;
    private String observerState;
    private ConcreteSubject subject;

    public ConcreteObserver2(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
    }

    public void update2() {
        observerState = subject.getSubjectState();
        System.out.println( name + "  的状态:"  + observerState);
    }
}
