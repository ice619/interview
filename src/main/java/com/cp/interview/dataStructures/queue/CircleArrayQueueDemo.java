package com.cp.interview.dataStructures.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("---------测试环形队列------");
        //说明4  队列有效最大数据为3
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据");
            System.out.println("g(get): 取出数据");
            System.out.println("h(head): 查看队列头数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    circleArrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("添加一个数");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleArrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleArrayQueue.headQueue();
                        System.out.printf("队列头数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出。。");

    }
}

class CircleArrayQueue{
    private int maxSize;//最大容量
    //注意这里front 含义调整，指向队列第一个元素，不是前一个了
    //初始值为0
    private int front;
    //rear含义调整，改为指向队列最后一个元素的后一个位置
    //初始值为0
    private int rear;
    private int[] arr;//存数据

    public CircleArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }
    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }
    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能添加数据");
            return;
        }

        arr[rear] = n;
        //rear 后移 要考虑取模
        rear = (rear + 1) % maxSize;
    }
    //获取队列数据，出队列
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据");
        }
        int temp = arr[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
        }
        //从front 开始遍历，遍历多少个元素？
        //front + 一个有效元素个数
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d \n", i % maxSize, arr[i % maxSize]);
        }
    }
    //求出当前队列有效个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
    //显示队列头数据，但不取
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}
