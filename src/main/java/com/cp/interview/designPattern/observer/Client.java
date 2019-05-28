package com.cp.interview.designPattern.observer;

public class Client {
    public static void main(String[] args) {
        ConcreteSubject subject1 = new ConcreteSubject();

        Observer observerA = new ConcreteObserver("观察者0", subject1);
        Observer observerB = new ConcreteObserver("观察者1", subject1);

        subject1.attach(observerA);
        subject1.attach(observerB);

        subject1.setSubjectState("报警。。");
        subject1.notifyToObserver();

    }
}
