package com.cp.interview.thread.base.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Description: 自旋锁
 *
 * @author chenpeng
 * @date 2019/8/26 11:51
 */
public class SpinLock implements Lock {
    private AtomicReference<Thread> sign = new AtomicReference<Thread> ();

    @Override
    public void lock() {
        System.out.println("开始lock");
        Thread currentThread = Thread.currentThread();
        while (!sign.compareAndSet(null, currentThread)){
            System.out.println(sign.get().getName() + "lock");
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        Thread currentThread = Thread.currentThread();
        System.out.println(sign.get().getName() + " 开始unlock");
        sign.compareAndSet(currentThread,null);
        System.out.println("unlock完成");
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}