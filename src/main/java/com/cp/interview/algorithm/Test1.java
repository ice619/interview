package com.cp.interview.algorithm;

import java.util.Scanner;

/**
 *
 */
public class Test1{
    public static void main(String[] args){
        Test1 test = new Test1();
        //跳台阶
//        test.jump(5);

        //字母全排列
//        char buf[] = {'A'};
//        test.allSort(buf,0, buf.length - 1);
//        char buf1[] = {'A','B'};
//        test.allSort(buf1,0, buf1.length - 1);
//        char buf2[] = {'A','B','C'};
//        test.allSort(buf2,0, buf2.length - 1);

        //杨氏数组查某个值
        int[][] arr = {{1,2,3},{2,3,4},{3,4,5}};
        test.getPosition(arr,3,3,7);
    }
    /**
     * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
     * 输入例子: 0xA  输出例子:10
     */
    public void hexConversion(){
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            String str=sc.next().substring(2);
            System.out.println(Integer.parseInt(str,16));
        }
    }

    /**
     * 跳台阶，每次只能一下跳一阶或者俩阶，一共n阶台阶看有几种跳法
     */
    public int jump(int n){
        if(n == 1){
            System.out.println(n + "台阶跳法数目:" + 1);
            return 1;
        }else if(n == 2){
            System.out.println(n + "台阶跳法数目:" + 2);
            return 2;
        }else {
            int num = 0;
            //num = jump(n -1) + jump(n - 2); //效率太低
            int first = 1;
            int second = 2;
            for(int i = 3; i <= n; i ++){
                num = first + second;
                first = second;
                second = num;
            }
            System.out.println(n + "台阶跳法数目:" + num);
            return num;
        }
    }

    /**
     * 字母全排列
     * 递归思想：比如abcd 可以分解成
     * 以a开头后面bcd全排列，
     * 以b开头后面acd全排列，以此类推
     */
    public void allSort(char[] buf, int start, int end){
        if(start == end){
            for(int i = 0; i <= end; i ++){
                System.out.print(buf[i]);
            }
            System.out.println();
        }else {
            for(int i = start; i <= end; i ++){
                //交换，将第i个字母放到数组首位
                char temp = buf[start];
                buf[start] = buf[i];
                buf[i] = temp;

                allSort(buf, start + 1, end);

                //将交换后的数组还原
                temp = buf[start];
                buf[start] = buf[i];
                buf[i] = temp;
            }
        }
    }

    /**
     *  杨氏数组，查询某个数是否存在
     *  思路从右上角开始比较
     */
    public void getPosition(int[][] arr,int row, int col, int num){

        int i = 0;
        int j = col - 1;
        while(i < row && j >= 0){
            if(num > arr[i][j]){
                i++;
            }else if(num < arr[i][j]){
                j --;
            }else {
                System.out.println("找到元素位置:(" + i + "," + j + ")");
                return;
            }
        }
        System.out.println("没有找到");
    }
}


