package com.cp.interview.thread.base.volatileDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: volatile 非原子性
 *  AtomicInteger  ??没起作用
 * @author chenpeng
 * @date 2019/8/23 16:36
 */
public class VolatileNoAtomic extends Thread {
    private static volatile int count;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static void addCount(){
        for (int i = 0; i < 1000; i++) {
            count++;
//             atomicInteger.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName()+ ":" + count);
//        System.out.println(Thread.currentThread().getName()+ ":" + atomicInteger.get());
    }
    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] arr = new VolatileNoAtomic[100];
        for(int i = 0; i < 50; i ++){
            arr[i] = new VolatileNoAtomic();
        }
        for(int i = 0; i < 50; i ++){
            arr[i].start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println("最终：" + VolatileNoAtomic.count);
//        System.out.println("最终：" + VolatileNoAtomic.atomicInteger.get());
    }

}