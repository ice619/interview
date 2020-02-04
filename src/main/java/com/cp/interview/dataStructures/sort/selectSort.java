package com.cp.interview.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序 时间复杂度O(n 平方)
 */
public class selectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -1, -40};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));

        //测试排序速度，给80000个数据测试
        int[] arr = new int[80000];
        for(int i = 0; i < arr.length; i ++){
            arr[i] = (int)(Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间=" + date1Str);

        selectSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间=" + date2Str);//2 秒

//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
    }

    //选择排序
    public static void selectSort(int[] arr){
        //算法：先简单 --》 再复制  就是可以把一个复制的算法，拆分成简单问题，逐步解决

        //推导后
        for(int i = 0; i < arr.length -1; i ++){
            int minIndex = i;
            int min = arr[i];
            for(int j = i + 1; j < arr.length; j ++){
                if(min > arr[j]){//说明假设的最小值，并不是最小
                    min = arr[j];
                    minIndex = j;
                }
            }
            //将最小值，放在arr[0],即交换
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

//            System.out.println("第"+(i + 1)+"轮：");
//            System.out.println(Arrays.toString(arr));
        }
        /*//第一轮
        int minIndex = 0;
        int min = arr[0];
        for(int j = 0 + 1; j < arr.length; j ++){
            if(min > arr[j]){//说明假设的最小值，并不是最小
                min = arr[j];
                minIndex = j;
            }
        }
        //将最小值，放在arr[0],即交换
        if(minIndex != 0){
            arr[minIndex] = arr[0];
            arr[0] = min;
        }

        System.out.println("第一轮：");
        System.out.println(Arrays.toString(arr));

        //第二轮轮
        minIndex = 1;
        min = arr[1];
        for(int j = 1 + 1; j < arr.length; j ++){
            if(min > arr[j]){//说明假设的最小值，并不是最小
                min = arr[j];
                minIndex = j;
            }
        }
        //将最小值，放在arr[0],即交换
        if(minIndex != 1){
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第二轮：");
        System.out.println(Arrays.toString(arr));

        //第3轮轮
        minIndex = 2;
        min = arr[2];
        for(int j = 2 + 1; j < arr.length; j ++){
            if(min > arr[j]){//说明假设的最小值，并不是最小
                min = arr[j];
                minIndex = j;
            }
        }
        //将最小值，放在arr[0],即交换
        if(minIndex != 2){
            arr[minIndex] = arr[2];
            arr[2] = min;
        }

        System.out.println("第3轮：");
        System.out.println(Arrays.toString(arr));*/

    }
}
