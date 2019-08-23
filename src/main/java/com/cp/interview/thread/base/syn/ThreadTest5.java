package com.cp.interview.thread.base.syn;

/**
 * Description:静态同步方法
 * 使用俩个线程，一个同步函数，一个同步代码块
 * 当同步代码块使用synchronized (mutex或this)，不能保证线程安全
 * 当同步代码块使用synchronized (ThreadTrain5.class)，则线程安全，证明静态同步方法是锁当前类的字节码文件，class对象
 * @author chenpeng
 * @date 2019/8/23 15:51
 */
public class ThreadTest5 {
    public static void main(String[] args) {
        ThreadTrain5 threadTrain5 = new ThreadTrain5();
        Thread thread1 = new Thread(threadTrain5,"一号窗口");
        Thread thread2 = new Thread(threadTrain5,"二号窗口");
        thread1.start();
        thread2.start();
    }
}

class ThreadTrain5 implements Runnable {
    //火车票数
    private static int trainCount = 100;
    private Object mutex = new Object();


    @Override
    public void run() {
        while (true){
//            synchronized (mutex) {
//            synchronized(this){
            synchronized(ThreadTrain5.class){
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


    public static synchronized void sale(){
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