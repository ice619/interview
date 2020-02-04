package com.cp.interview.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序 时间复杂度 O(n 平方)  （俩个for循环）
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, 20};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));

        //测试排序速度，给80000个数据测试
        int[] arr = new int[80000];
        for(int i = 0; i < arr.length; i ++){
            arr[i] = (int)(Math.random() * 8000000);
        }
//        System.out.println(Arrays.toString(arr));

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间=" + date1Str);
        //排序
        bubbleSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间=" + date2Str);//11秒

//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));

        /*//第一趟排序，将最大的数排在最后
        int temp = 0;
        for(int j = 0; j < arr.length - 1; j++){
            //如果前面的比后面大 就交换
            if(arr[j] > arr[j + 1]){
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第一次排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第二次排序
        for(int j = 0; j < arr.length - 1 -1; j++){
            //如果前面的比后面大 就交换
            if(arr[j] > arr[j + 1]){
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第二次排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第三次排序
        for(int j = 0; j < arr.length - 1 -1 -1; j++){
            //如果前面的比后面大 就交换
            if(arr[j] > arr[j + 1]){
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第三次排序后的数组");
        System.out.println(Arrays.toString(arr));

        //第四次排序
        for(int j = 0; j < arr.length - 1 -1-1-1; j++){
            //如果前面的比后面大 就交换
            if(arr[j] > arr[j + 1]){
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        System.out.println("第四次排序后的数组");
        System.out.println(Arrays.toString(arr));*/

    }
    //冒泡排序
    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;//标识变量是否进行过交换
        for(int i = 0; i < arr.length -1; i ++){

            for(int j = 0; j < arr.length - 1 -i; j++){
                //如果前面的比后面大 就交换
                if(arr[j] > arr[j + 1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第"+(i + 1)+"次排序后的数组");
//            System.out.println(Arrays.toString(arr));
            if(!flag){//在一趟排序中，一次交换都没有发生
                break;
            }else {
                flag = false;//重置，进行下次判断
            }
        }
    }
}
