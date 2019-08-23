package com.cp.interview.thread.base.syn;

/**
 * Description:同步代码块
 *
 * @author chenpeng
 * @date 2019/8/23 14:10
 */
public class ThreadTest2 {
    public static void main(String[] args) {
        ThreadTrain2 threadTrain2 = new ThreadTrain2();
        Thread thread1 = new Thread(threadTrain2);
        Thread thread2 = new Thread(threadTrain2);
        thread1.start();
        thread2.start();
    }
}

class ThreadTrain2 implements Runnable {
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

    //自定义多线程同步锁
    private Object mutex = new Object();

    public void sale(){
        synchronized (mutex){
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

}
