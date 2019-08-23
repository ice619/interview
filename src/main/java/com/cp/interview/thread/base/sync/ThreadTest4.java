package com.cp.interview.thread.base.sync;

/**
 * Description:
 * 使用俩个线程，一个同步函数，一个同步代码块
 * 当同步代码块使用synchronized (mutex)，不能保证线程安全
 * 当同步代码块使用synchronized (this)，则线程安全，证明同步函数也是使用的this锁
 * @author chenpeng
 * @date 2019/8/24 14:45
 */
public class ThreadTest4 {
    public static void main(String[] args) {
        ThreadTrain4 threadTrain4 = new ThreadTrain4();
        Thread thread1 = new Thread(threadTrain4,"一号窗口");
        Thread thread2 = new Thread(threadTrain4,"二号窗口");
        thread1.start();
        thread2.start();
    }
}

class ThreadTrain4 implements Runnable {
    //火车票数
    private int trainCount = 100;
    private Object mutex = new Object();


    @Override
    public void run() {
        while (true){
//            synchronized (mutex) {
            synchronized(this){
                if(trainCount > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"出售第" + (100 - trainCount + 1 ) + "张票");
                    trainCount --;
                }
            }
            sale();
        }

    }


    public synchronized void sale(){
        if(trainCount > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"出售第" + (100 - trainCount + 1 ) + "张票");
            trainCount --;
        }
    }

}