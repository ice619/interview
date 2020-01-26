package com.cp.interview.dataStructures.stack;

import java.util.Scanner;

/**
 * 栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("show:显示栈");
            System.out.println("exit:");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据是：" + res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                 default:
                     break;

            }

        }
        System.out.println("程序退出");
    }
}

class ArrayStack{
    private int maxSize;//栈大小
    private int[] stack;//存数据
    private int top = -1;//top表示栈顶

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return  top == -1;
    }

    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top ++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top --;
        return value;
    }
    //显示栈（遍历）便利时，需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        //从栈顶开始显示数据
        for(int i = top; i >=0; i--){
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }
}
