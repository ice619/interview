package com.cp.interview.designPattern.singleton.eager;

/**
 * Description: 单例模式：饿汉式
 *
 * @author chenpeng
 * @date 2019/8/22 18:18
 */
public class EagerSingleton {
    private static EagerSingleton singletonInstance = new EagerSingleton();
    private EagerSingleton(){}

    public static EagerSingleton getInstance(){
        return singletonInstance;
    }

    public static void main(String[] args) {
        EagerSingleton instance1 = EagerSingleton.getInstance();
        EagerSingleton instance2 = EagerSingleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}