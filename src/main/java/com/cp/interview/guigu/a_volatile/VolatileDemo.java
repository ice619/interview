package com.cp.interview.guigu.a_volatile;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author chenpeng
 * @date 2019/4/30 17:29
 */

class MyData{
//    volatile int number = 0;
    int number = 0;

    public void addT060(){
        this.number = 60;
    }

}

/**
 * 1 验证volatile 可见性
 *      1.1 假如 int number = 0; number变量之前根本没有添加volatile关键字，没有可见性
 *      volatile强制线程每次读取该值的时候都去“主内存”中取值
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addT060();
            System.out.println(Thread.currentThread().getName() + " updated number value:" + myData.number);
        }, "AAA").start();

        //第二个线程就是main线程
        while(myData.number == 0){
            //main线程一直在此处循环
        }

        System.out.println(Thread.currentThread().getName() + " mission is over，main get number value:" + myData.number);
    }
}
