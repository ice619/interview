package com.cp.interview.thread.base.sync;

/**
 * Description:同步函数
 *
 * @author chenpeng
 * @date 2019/8/23 14:29
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        ThreadTrain3 threadTrain3 = new ThreadTrain3();
        Thread thread1 = new Thread(threadTrain3);
        Thread thread2 = new Thread(threadTrain3);
        thread1.start();
        thread2.start();
    }
}

class ThreadTrain3 implements Runnable {
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