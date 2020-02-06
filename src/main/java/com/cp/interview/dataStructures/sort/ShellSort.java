package com.cp.interview.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序（缩小增量排序）
 * 是对插入排序的一种优化
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        //测试排序速度，给80000个数据测试
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间=" + date1Str);

//        shellSort(arr);//交换式  6秒
        shellSort2(arr);//移位式（相当于缩小增量 + 在增量分组里面使用插入排序）不到一秒

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间=" + date2Str);// 6秒
    }

    public static void shellSort(int arr[]) {
        int temp = 0;
        //推导后 交换法
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有元素（共gap组，）步长是gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素 > 加上步长后那个元素，交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("第轮排序");
//            System.out.println(Arrays.toString(arr));
        }

        /*int temp = 0;
        //第一轮
        //将10个数据分成5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有元素（工5组，每组2个元素）步长是5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素 > 加上步长后那个元素，交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一轮排序");
        System.out.println(Arrays.toString(arr));

        //第2轮
        //将10个数据分成5/2 = 2组
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素 > 加上步长后那个元素，交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第2轮排序");
        System.out.println(Arrays.toString(arr));

        //第3轮
        //将10个数据分成2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素 > 加上步长后那个元素，交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第3轮排序");
        System.out.println(Arrays.toString(arr));*/
    }

    //对交换式希尔排序进行优化， 改为移位法
    public static void shellSort2(int[] arr) {
        //增量gap 逐步减小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //退出循环后，temp找到了插入位置
                    arr[j] = temp;
                }
            }
//            System.out.println("第轮排序");
//            System.out.println(Arrays.toString(arr));
        }
    }
}

