package com.cp.interview.thread.base.threadLocal;

/**
 * Description:ThreadLocal
 * ThreadLoca通过map集合
 * Map.put(“当前线程”,值)；
 *
 * @author chenpeng
 * @date 2019/8/23 17:13
 */
public class ThreadLocalDemo extends Thread {
    private Res res;

    public ThreadLocalDemo(Res res) {
        this.res = res;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + "i---" + i + "--num:" + res.getNum());
        }

    }

    public static void main(String[] args) {
        Res res = new Res();
        ThreadLocalDemo threadLocalDemo1 = new ThreadLocalDemo(res);
        ThreadLocalDemo threadLocalDemo2 = new ThreadLocalDemo(res);
        ThreadLocalDemo threadLocalDemo3 = new ThreadLocalDemo(res);
        threadLocalDemo1.start();
        threadLocalDemo2.start();
        threadLocalDemo3.start();
    }
}

class Res{
    public static Integer count = 0;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public Integer getNum() {
        int count = threadLocal.get() + 1;
        threadLocal.set(count);
        return count;
    }
}