package com.cp.interview.designPattern.singleton.iodh;

/**
 * Description: 单例模式
 *
 * 饿汉式单例类不能实现延迟加载，不管将来用不用始终占据内存；懒汉式单例类线程安全控
 * 制烦琐，而且性能受影响。可见，无论是饿汉式单例还是懒汉式单例都存在这样那样的问
 * 题，有没有一种方法，能够将两种单例的缺点都克服，而将两者的优点合二为一呢？答案
 * 是：Yes！下面我们来学习这种更好的被称之为Initialization Demand Holder (IoDH)的技术
 *
 * 在单例类中增加一个静态(static)内部类，在该内部类中创建单例对象，再将
 * 该单例对象通过getInstance()方法返回给外部使用
 *
 * JVM在类的初始化阶段（即在Class被加载后，且被线程使用之前），会执行类的初始化。
 * 在执行类的初始化期间，JVM会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化。
 *
 * @author chenpeng
 * @date 2019/8/22 16:01
 */
public class Singleton {
    private Singleton(){}

    private static class HolderClass{
        private static final Singleton INSTANCE = new Singleton();
    }

    private static Singleton getInstance() {
        return HolderClass.INSTANCE;
    }

    public static void main(String[] args) {
        Singleton s1, s2;
        s1 = Singleton.getInstance();
        s2 = Singleton.getInstance();

        System.out.println(s1 == s2);
    }
}