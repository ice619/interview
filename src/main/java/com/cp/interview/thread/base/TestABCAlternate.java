package com.cp.interview.thread.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程交替
 */
public class TestABCAlternate {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public static void main(String[] args) {
        TestABCAlternate ts = new TestABCAlternate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 20; i ++){
                    ts.loopA(i);
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 20; i ++){
                    ts.loopB(i);
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 20; i ++){
                    ts.loopC(i);
                }
            }
        }, "C").start();
    }
    public void loopA(int totalLoop){
        lock.lock();
        try {
            if (number != 1){
                condition1.await();
            }
            for(int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName() + i + totalLoop);
            }
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loopB(int totalLoop){
        lock.lock();
        try {
            if (number != 2){
                condition2.await();
            }
            for(int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName() + i + totalLoop);
            }
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loopC(int totalLoop){
        lock.lock();
        try {
            if (number != 3){
                condition3.await();
            }
            for(int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName() + i + totalLoop);
            }
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
