package com.cp.interview.designPattern.singleton.lazy;

/**
 * Description: 单例模式：懒汉式
 *
 * @author chenpeng
 * @date 2019/8/22 18:25
 */
public class LazySingleton {
    private static LazySingleton instance = null;
    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if(instance == null){
            synchronized (LazySingleton.class){
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        LazySingleton instance1 = LazySingleton.getInstance();
        LazySingleton instance2 = LazySingleton.getInstance();

        System.out.println(instance1 == instance2);
    }
}