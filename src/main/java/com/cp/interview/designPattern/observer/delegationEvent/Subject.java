package com.cp.interview.designPattern.observer.delegationEvent;

public abstract class Subject {

    private EventHandler eventHandler = new EventHandler();

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public abstract void addListener(Object object, String methodName, Object... params);

    public abstract void notifyToObserver();
}
