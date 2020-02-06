package com.cp.interview.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8,4,5,7,1,3,6,2};

        //测试排序速度，给80000个数据测试
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间=" + date1Str);

        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间=" + date2Str);// 不到1秒

//        System.out.println("排序后=" + Arrays.toString(arr));
    }

    //分+合
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right ) / 2;
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr,mid + 1, right, temp);

            //合并!!  注意对这一步的理解  一共合并arr.length - 1次，时间复杂度不高
            merge(arr, left, mid, right, temp);
//            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 合并方法
     * @param arr
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;//左边有序序列的初始索引
        int j = mid + 1;//右边有序序列初始索引
        int t = 0;//temp数组的当前索引

        //(一)
        //先把左右俩边（有序）的数据，按规则填充到temp数组
        //直到左右俩边有序序列，有一边处理完毕为止
        while(i <= mid && j <= right){
            //如果左边的当前元素小雨点等于右边当前元素
            //即将左边当前元素拷贝到temp
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else {//反之
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //（二）
        //把有剩余数据的一边数据依次填充到temp
        while(i <= mid){//说明左边有剩余
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while(j <= right){//说明左边有剩余
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //(三)
        //将temp数组的元素拷贝到arr
        //注意并不是每次都拷贝所有数据，因为合并是个逐步过程，最后一次才拷贝的全部数据
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            //第一次合并 tempLeft=0，right=1
            //第2次合并 tempLeft=2，right=3
            //第3次合并 tempLeft=0，right=3
            //最后一次 tempLeft=0，right=7
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }
}
