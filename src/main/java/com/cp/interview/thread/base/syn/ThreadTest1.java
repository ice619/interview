package com.cp.interview.thread.base.syn;

/**
 * Description: 演示线程安全问题
 *
 * @author chenpeng
 * @date 2019/8/23 13:59
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        ThreadTrain1 threadTrain1 = new ThreadTrain1();
        Thread thread1 = new Thread(threadTrain1);
        Thread thread2 = new Thread(threadTrain1);
        thread1.start();
        thread2.start();
    }
}

class ThreadTrain1 implements Runnable {
    //火车票数
    private int trainCount = 100;

    @Override
    public void run() {
        while(trainCount > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sale();
        }
    }

    public void sale(){
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
