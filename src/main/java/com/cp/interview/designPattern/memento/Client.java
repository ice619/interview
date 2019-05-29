package com.cp.interview.designPattern.memento;

/**
 * 备忘录模式
 */
public class Client {
    public static void main(String[] args) {
        //初始状态
        Originator originator = new Originator();
        originator.setState("on");
        originator.show();

        //保存状态
        CareTaker careTaker = new CareTaker();
        careTaker.setMemento(originator.createMemento());

        //修改转态
        originator.setState("off");
        originator.show();

        //恢复初始状态
        originator.restoreMemento(careTaker.getMemento());
        originator.show();


    }
}
