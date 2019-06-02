package com.cp.interview.designPattern.mediator;

/**
 * 中介者模式
 */
public class Client {
    public static void main(String[] args) {
        ConcreteMediator concreteMediator = new ConcreteMediator();

        ConcreteColleague1 concreteColleague1 = new ConcreteColleague1(concreteMediator);
        ConcreteColleague2 concreteColleague2 = new ConcreteColleague2(concreteMediator);

        concreteMediator.setConcreteColleague1(concreteColleague1);
        concreteMediator.setConcreteColleague2(concreteColleague2);

        concreteColleague1.send("吃饭了么");
        concreteColleague2.send("没有");
    }
}
