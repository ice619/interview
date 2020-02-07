package com.cp.interview.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序（桶排序）
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
        //测试排序速度，给80000个数据测试
        // 800000000 * 11 * 4 /1024 /1024/ 1024 = x G
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前时间=" + date1Str);

        radixSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间=" + date2Str);// 不到1秒
    }

    public static void radixSort(int[] arr){

        //推导后
        //先得到数组中最大的位数
        int max = arr[0];
        for(int i = 1; i < arr.length; i ++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大位数
        int maxLength = (max + "").length();

        //定义一个二维数据，表示10个桶，每个桶是一个一维数组
        //二维数组包含10个一维数组
        //为防止溢出，每个一维数组大小定为arr.length
        //基数排序是使用空间换时间算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少数据,
        // 定义一个一维数组，记录各个桶每次放入的数据个数
        //bucketElementCounts[0] 记录bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        for(int i = 0, n = 1; i < maxLength; i ++, n *= 10){
            //针对每个元素对应的位数排序，个位，十位，百位
            for(int j = 0; j < arr.length; j ++){
                //取出每个元素对应位
                int digitOfElement = arr[j] / n % 10;
                //放入到对应桶
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement] ++;
            }
            //按照桶的顺序，（一维数组的下标一次取出数据，放入原来数组）
            int index = 0;
            //遍历每个桶，并将桶数据放入原数组
            for(int k = 0; k < bucketElementCounts.length; k ++){
                //如果桶中有数据，才放入原数组
                if(bucketElementCounts[k] != 0){
                    //循环该桶
                    for(int l = 0; l < bucketElementCounts[k]; l ++){
                        //取出元素放入Arr
                        arr[index ++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，bucketElementCounts[k] = 0；
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第"+ (i + 1) +"轮，对个位排序处理=" + Arrays.toString(arr));

        }

       /* //第一轮(针对元素个位进行排序)
        for(int j = 0; j < arr.length; j ++){
            //取出每个元素各位
            int digitOfElement = arr[j] % 10;
            //放入到对应桶
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement] ++;
        }
        //按照桶的顺序，（一维数组的下标一次取出数据，放入原来数组）
        int index = 0;
        //遍历每个桶，并将桶数据放入原数组
        for(int k = 0; k < bucketElementCounts.length; k ++){
            //如果桶中有数据，才放入原数组
            if(bucketElementCounts[k] != 0){
                //循环该桶
                for(int l = 0; l < bucketElementCounts[k]; l ++){
                    //取出元素放入Arr
                    arr[index ++] = bucket[k][l];
                }
            }
            //第一轮处理后，bucketElementCounts[k] = 0；
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮，对个位排序处理=" + Arrays.toString(arr));

        //===========================================
        //第二轮
        for(int j = 0; j < arr.length; j ++){
            //取出每个元素十位
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应桶
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement] ++;
        }
        //按照桶的顺序，（一维数组的下标一次取出数据，放入原来数组）
        index = 0;
        //遍历每个桶，并将桶数据放入原数组
        for(int k = 0; k < bucketElementCounts.length; k ++){
            //如果桶中有数据，才放入原数组
            if(bucketElementCounts[k] != 0){
                //循环该桶
                for(int l = 0; l < bucketElementCounts[k]; l ++){
                    //取出元素放入Arr
                    arr[index ++] = bucket[k][l];
                }
            }
            //第2轮处理后，bucketElementCounts[k] = 0；
            bucketElementCounts[k] = 0;
        }
        System.out.println("第2轮，对个位排序处理=" + Arrays.toString(arr));

        //===========================================
        //第3轮
        for(int j = 0; j < arr.length; j ++){
            //取出每个元素百位
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应桶
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement] ++;
        }
        //按照桶的顺序，（一维数组的下标一次取出数据，放入原来数组）
        index = 0;
        //遍历每个桶，并将桶数据放入原数组
        for(int k = 0; k < bucketElementCounts.length; k ++){
            //如果桶中有数据，才放入原数组
            if(bucketElementCounts[k] != 0){
                //循环该桶
                for(int l = 0; l < bucketElementCounts[k]; l ++){
                    //取出元素放入Arr
                    arr[index ++] = bucket[k][l];
                }
            }
            //第3轮处理后，bucketElementCounts[k] = 0；
            bucketElementCounts[k] = 0;
        }
        System.out.println("第3轮，对个位排序处理=" + Arrays.toString(arr));
*/
    }

}
