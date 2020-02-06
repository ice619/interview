package com.cp.interview.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序 时间复杂度 O(n 平方)
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
        //测试排序速度，给80000个数据测试
        int[] arr = new int[80000];
        for(int i = 0; i < arr.length; i ++){
            arr[i] = (int)(Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间=" + date1Str);

        insertSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间=" + date2Str);// 1秒
    }

    //插入排序
    public static void insertSort(int[] arr){

        for(int i = 1; i < arr.length; i ++){
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1;//即arr[1] 的前面这个数的下标

            //给insertVal 找到插入的位置
            //insertIndex >= 0 保证给insertVal 找插入位置，不越界
            //insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            //就需要将arr[insertIndex] 后移
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex --;
            }
            //当退出循环时，说明插入位置找到，insertIndex + 1;
            //判断是否需要赋值
            if(insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }

//            System.out.println("第"+ i +"轮插入");
//            System.out.println(Arrays.toString(arr));
        }

        /*//逐步推导
        //第一轮{101, 34, 119, 1} =》{ 34, 101, 119, 1}
        //定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1 - 1;//即arr[1] 的前面这个数的下标

        //给insertVal 找到插入的位置
        //insertIndex >= 0 保证给insertVal 找插入位置，不越界
        //insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        //就需要将arr[insertIndex] 后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }
        //当退出循环时，说明插入位置找到，insertIndex + 1;
        arr[insertIndex + 1] = insertVal;

        System.out.println("第一轮插入");
        System.out.println(Arrays.toString(arr));

        //第二轮
        insertVal = arr[2];
        insertIndex = 2 - 1;
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }
        arr[insertIndex + 1] = insertVal;

        System.out.println("第二轮插入");
        System.out.println(Arrays.toString(arr));

        //第三轮
        insertVal = arr[3];
        insertIndex = 3 - 1;
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }
        arr[insertIndex + 1] = insertVal;

        System.out.println("第三轮插入");
        System.out.println(Arrays.toString(arr));*/
    }
}

