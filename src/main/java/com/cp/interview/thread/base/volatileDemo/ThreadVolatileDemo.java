package com.cp.interview.thread.base.volatileDemo;

/**
 * Description: volatile可见性
 * 强制线程每次读取该值的时候都去“主内存”中取值
 *
 * @author chenpeng
 * @date 2019/8/23 16:31
 */
public class ThreadVolatileDemo extends Thread {
//    public boolean flag = true;
    public volatile boolean flag = true;
    @Override
    public void run() {
        System.out.println("开始执行子线程....");
        while (flag) {
        }
        System.out.println("线程停止");
    }
    public void setRuning(boolean flag) {
        this.flag = flag;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo threadVolatileDemo = new ThreadVolatileDemo();
        threadVolatileDemo.start();
        Thread.sleep(3000);
        threadVolatileDemo.setRuning(false);
        System.out.println("flag 已经设置成false");
        Thread.sleep(1000);
        System.out.println(threadVolatileDemo.flag);

    }
}
