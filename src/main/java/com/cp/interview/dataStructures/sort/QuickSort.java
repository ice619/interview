package com.cp.interview.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70};

        //测试排序速度，给80000个数据测试
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间=" + date1Str);

        quickSort(arr,0, arr.length - 1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间=" + date2Str);// 不到1秒
//        System.out.println("arr= " + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left;//左下标
        int r = right;//右下标
        int pivot = arr[(left + right) / 2];//pivot 中轴
        int temp = 0;//交换时临时变量
        //while循环目的是让比pivot值小的放到左边，大放右边
        while(l < r){
            //在pivot左边一直找，直到找到一个值>=pivot值才退出
            while(arr[l] < pivot){
                l += 1;
            }
            //在pivot右边一直找，直到找到一个值<=pivot值才退出
            while(arr[r] > pivot){
                r -= 1;
            }
            //如果l >= r 说明pivot左右俩边值，
            //已经左边全部<=pivot   右边>=pivot
            if(l >= r){
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现arr[l] == pivot，则r --,前移
            if(arr[l] == pivot){
                r -= 1;
            }
            //如果交换完后，发现arr[r] == pivot，则l --,后移
            if(arr[r] == pivot){
                l += 1;
            }
        }
        //如果l == r 必须 l ++ , r -- 否则会出现栈溢出 java.lang.StackOverflowError
        if(l == r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if(left < r){
            quickSort(arr, left, r);
        }

        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }
    }
}
