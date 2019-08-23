package com.cp.interview.thread.base.sync;

/**
 * Description: 死锁
 *
 * @author chenpeng
 * @date 2019/8/23 15:36
 */
public class ThreadTest6 {
    public static void main(String[] args) {
        ThreadTrain6 threadTrain6 = new ThreadTrain6();
        Thread thread1 = new Thread(threadTrain6,"一号窗口");
        Thread thread2 = new Thread(threadTrain6,"二号窗口");
        thread1.start();
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadTrain6.flag = false;
        thread2.start();
    }
}

class ThreadTrain6 implements Runnable {
    //火车票数
    private int trainCount = 100;
    private Object mutex = new Object();
    public boolean flag = true;


    @Override
    public void run() {
        if (flag){
            while (true){
                synchronized (mutex){
                    // 锁(同步代码块)在什么时候释放？ 代码执行完， 自动释放锁.
                    // 如果flag为true 先拿到 obj锁,在拿到this 锁、 才能执行。
                    // 如果flag为false先拿到this,在拿到obj锁，才能执行。
                    // 死锁解决办法:不要在同步中嵌套同步。

                    sale();
                }
            }
        }else {
            while (true){
                sale();
            }
        }


    }


    public synchronized void sale(){
        synchronized (mutex) {
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