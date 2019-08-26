package com.cp.interview.thread.base.juc.blockqueue;
import	java.util.concurrent.LinkedBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 阻塞队列BlockingQueue
 *
 * @author chenpeng
 * @date 2019/8/26 10:40
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String> ();
        ProducerThread producerThread1 = new ProducerThread(queue);
        ProducerThread producerThread2 = new ProducerThread(queue);
        ConsumerThread consumerThread = new ConsumerThread(queue);

        Thread t1 = new Thread(producerThread1);
        Thread t2 = new Thread(producerThread2);

        Thread c = new Thread(consumerThread);

        t1.start();
        t2.start();
        c.start();

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producerThread1.stop();
        producerThread2.stop();

    }
}

class ProducerThread implements Runnable {
    private BlockingQueue queue;
    private volatile boolean flag = true;
    private static AtomicInteger count = new AtomicInteger();

    public ProducerThread(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (flag) {
                System.out.println("生产线程启动。。");
                System.out.println("正在生产数据，，");
                String data = count.incrementAndGet() + "";
                //将数据存入队列
                boolean offer = queue.offer(data, 2, TimeUnit.SECONDS);
                if (offer){
                    System.out.println("生产者,存入" + data + "到队列中,成功.");
                } else {
                    System.out.println("生产者,存入" + data + "到队列中,失败.");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者退出线程");
        }
    }
    public void stop() {
        this.flag = false;
    }
}

class ConsumerThread implements Runnable {
    private BlockingQueue<String> queue;
    private volatile boolean flag = true;

    public ConsumerThread(BlockingQueue<String> queue) {
        this.queue = queue;

    }

    @Override
    public void run() {
        try {
            System.out.println("消费线程启动...");
            System.out.println("消费者,正在从队列中获取数据..");
            //取出数据
            String data = queue.poll(2, TimeUnit.SECONDS);
            if (data != null) {
                System.out.println("消费者,拿到队列中的数据data:" + data);
                Thread.sleep(1000);
            } else {
                System.out.println("消费者,超过2秒未获取到数据..");
                flag = false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("消费者退出线程...");
        }


    }
}