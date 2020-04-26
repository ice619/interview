package com.cp.interview.dataStructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序
 * 升序 - 大顶堆（每次将堆顶最大的根节点放到数组最后，到最后就排好序了）
 * 降序 - 小顶堆
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {4,6,8,5,9};
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

        heapSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后时间=" + date2Str);// 不到1秒

//        System.out.println("排序后" + Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        int temp = 0;
        System.out.println("堆排序");
        //分步完成
       /* adjustHeap(arr,1,arr.length);
        System.out.println("第一轮" + Arrays.toString(arr));
        adjustHeap(arr,0,arr.length);
        System.out.println("第2轮" + Arrays.toString(arr));*/

       for(int i = arr.length / 2 - 1; i >= 0; i --){
            adjustHeap(arr, i, arr.length);
       }
//        System.out.println("大顶堆" + Arrays.toString(arr));

       //堆顶元素与末尾元素交换
        //重新调整结构，
       for(int j = arr.length - 1; j > 0; j--){
           //交换
           temp = arr[j];
           arr[j] = arr[0];
           arr[0] = temp;
           adjustHeap(arr, 0, j);
       }
    }

    //将一个数组 调整成一个大顶堆
    /**
     * 将以 i 对应的非叶子节点的局部树调整成大顶堆
     * 举例{4,6,8,5,9} --》i = 1  得到{4,9,8,5,6}
     * 如果再次调用adjustHeap 传入i=0
     * @param arr
     * @param i 非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整，length 会逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];//先取出当前元素的值保存到临时变量
        //k = i * 2 + 1 是i节点的左子节点
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1){
            if(k + 1 < length && arr[k] < arr[k + 1]){//说明左子节点的值小于右子节点的值
                k ++;//k指向右子节点
            }
            if(arr[k] > temp){//如果子节点大于父节点
                arr[i] = arr[k];//把较大值赋给当前节点
                i = k;//i 指向 K ,继续循环比较
            }else {
                break;//因为到时候是从左到右，从下到上调整，所以这里可以break
            }
        }
        //for循环结束后，我们已经将以i 为父节点的数的最大值，放在了顶上，只是这个局部
        arr[i] = temp;//将temp放到调整后的位置
    }

}
