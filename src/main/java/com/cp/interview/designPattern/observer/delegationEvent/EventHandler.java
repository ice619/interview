package com.cp.interview.designPattern.observer.delegationEvent;

import com.cp.interview.designPattern.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件处理者
 */
public class EventHandler{
    private List<Event> objects;

    public EventHandler() {
        objects = new ArrayList<>();
    }

    /**
     * 添加某个对象要执行的事件，及需要的参数
     */
    public void addEvent(Object object, String methodName, Object... params){
        objects.add(new Event(object, methodName, params));
    }

    /**
     * 通知所有的对象执行指定的事件
     * @throws Exception
     */
    public void notifyToObserver() throws Exception{
        for (Event event : objects){
            event.invoke();
        }
    }

}
