package com.cp.interview.dataStructures.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 */
public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index=" + fibSearch(arr, 89));
    }
    //mid = low + F (k - 1) - 1
    //非递归方法得到一个斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2; i < maxSize; i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    /**
     * 斐波那契查找算法
     * @param arr
     * @param key
     * @return
     */
    public static int fibSearch(int[] arr, int key){
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;
        int[] f = fib();
        //获取斐波那契分割数值下标
        while(high > f[k] - 1){
            k ++;
        }
        //因为f[k] 的值可能大于a的长度，需要Arrays类构造一个新数组，并指向a[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //实际需求使用a数组最后的数值填充Temp
        for(int i = high + 1; i < temp.length; i++){
            temp[i] = arr[high];
        }

        while(low <= high){
            mid = low + f[k - 1] -1;
            if(key < temp[mid]){//向数组前面查找
                high = mid -1;
                //1.全部元素= 前面元素+ 后面元素
                //2.f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-1]个元素，所以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]的前面继续查找k--
                //即下次循环mid = f[k-1-1]-1
                k --;
            }else if(key > temp[mid]){//继续向数组后面查找
                low = mid + 1;
                //1.全部元素= 前面元素+ 后面元素
                //2.f[k] = f[k-1] + f[k-2]
                //因为后面有f[k-2] 所以可以继续拆分f[k-1] = f[k-3] + f[k-4]
                //即在f[k-2]的前面继续查找k -=2
                //即下次循环mid = f[k-1-2]-1
                k -= 2;
            }else {
                //找到，需确定返回的哪个下标
                if(mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}

