package com.cp.interview.designPattern.observer.delegationEvent;

public class ConcreteSubject extends Subject {

    private String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
    }
    @Override
    public void addListener(Object object, String methodName, Object... params) {
        System.out.println("有新的观察者委托放哨人");
        EventHandler handler = this.getEventHandler();
        handler.addEvent(object, methodName, params);
    }

    @Override
    public void notifyToObserver() {
        System.out.println("放哨人通知观察者。。。");
        try {
            this.getEventHandler().notifyToObserver();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
