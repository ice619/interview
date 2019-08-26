package com.cp.interview.thread.base.lock;

/**
 * Description: 自旋锁
 *
 * @author chenpeng
 * @date 2019/8/26 11:44
 */
public class SpinLockTest implements Runnable {
    static int sum;
    private SpinLock spinLock;

    public SpinLockTest(SpinLock spinLock) {
        this.spinLock = spinLock;
    }

    @Override
    public void run() {
        this.spinLock.lock();
        sum++;
        this.spinLock.unlock();
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        for (int i = 0; i < 10; i++) {
            SpinLockTest spinLockTest = new SpinLockTest(spinLock);
            Thread thread = new Thread(spinLockTest);
            thread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum);

    }
}