package com.cp.interview.thread.base;

/**
 * 使用匿名内部类创建线程
 * join作用是让其他线程变为等待
 */
public class Thread1 {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    System.out.println("子线程 i：" + i);
                }
            }
        });

        t.start();
        try {
            //主线程调用了t.join()，表示主线程将执行权让给t线程，等t线程执行完之后，主线程才执行
            //join 加入，表示t线程加入进来
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("主线程 i：" + i);
        }

    }
}
