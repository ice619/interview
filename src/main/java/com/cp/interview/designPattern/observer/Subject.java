package com.cp.interview.designPattern.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    //定义集合用于存储所有观察者对象
    protected List<Observer> observers = new ArrayList();

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }

    public void notifyToObserver(){
        for (Observer o : observers){
            o.update();
        }
    }
}
