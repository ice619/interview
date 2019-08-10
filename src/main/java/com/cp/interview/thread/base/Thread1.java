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

        for (int i = 1; i < 10; i++) {
            System.out.println("主线程 i：" + i);
        }

    }
}
