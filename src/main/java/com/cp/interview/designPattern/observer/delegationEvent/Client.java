package com.cp.interview.designPattern.observer.delegationEvent;

import com.cp.interview.designPattern.observer.delegationEvent.ConcreteSubject;
import com.cp.interview.designPattern.observer.delegationEvent.ConcreteObserver1;
import com.cp.interview.designPattern.observer.delegationEvent.ConcreteObserver2;

/**
 * 事件委托
 * 如果具体观察者有各自不同的方法，就不能用Obsesrver接口了，用Event  EventHandler 反射代替了Observer抽象接口
 */
public class Client {
    public static void main(String[] args) {

        ConcreteSubject subject1 = new ConcreteSubject();

        ConcreteObserver1 observer1 = new ConcreteObserver1("观察者1", subject1);
        ConcreteObserver2 observer2 = new ConcreteObserver2("观察者2", subject1);

        //如果方法没有参数就不传，
        subject1.addListener(observer1,"update1");
        subject1.addListener(observer2,"update2");

        subject1.setSubjectState("有情况");
        subject1.notifyToObserver();

    }
}
