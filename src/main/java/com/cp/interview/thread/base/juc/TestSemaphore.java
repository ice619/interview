package com.cp.interview.thread.base.juc;
import java.util.Random;
import	java.util.concurrent.Semaphore;

/**
 * Description: 信号量
 *
 * @author chenpeng
 * @date 2019/8/26 10:19
 */
public class TestSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <= 10; i++) {
            ToWc  toWc = new ToWc("第"+i+"个人,",semaphore);
            new Thread(toWc).start();
        }
    }
}

class ToWc implements Runnable {
    private String name;
    private Semaphore wc;

    public ToWc(String name, Semaphore wc) {
        this.name = name;
        this.wc = wc;
    }

    @Override
    public void run() {
        try {
            //剩余资源
            int availablePermits = wc.availablePermits();
            if(availablePermits > 0){
                System.out.println(name + ":有位置，剩余："+availablePermits);
            }else {
                System.out.println(name + ":没位置了，剩余："+availablePermits);
            }

            //申请资源,一直到申请到为止
            wc.acquire();
            System.out.println(name + "开始使用。。");
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(name + "使用结束");
            wc.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}