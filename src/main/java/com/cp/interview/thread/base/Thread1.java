package com.cp.interview.thread.base;

/**
 * 使用匿名内部类创建线程
 */
public class Thread1 {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    System.out.println("子线程 i：" + i);
                }
            }
        });

        thread.start();
        try {
            //主线程调用了hread.join()，表示主线程将执行权让给thread线程，等thread线程执行完之后，主线程才执行
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < 10; i++) {
            System.out.println("主线程 i：" + i);
        }

    }
}
