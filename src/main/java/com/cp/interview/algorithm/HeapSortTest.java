package com.cp.interview.algorithm;

import java.util.Arrays;

/**
 * 将数组转化为堆，递归，从最简单的堆化
 * 从数组中第一个非叶子节点的最小二叉树堆化
 * 第一个非叶子节点索引位置公式：int i = arr.length / 2 - 1
 * 然后遍历，完成堆化
 * for(int i = arr.length / 2 - 1; i >= 0; i --){
 *             adjustHeap(arr, i, arr.length);
 *        }
 * i的左子节点索引 k = i * 2 + 1
 */
public class HeapSortTest {
    public static void main(String[] args){
//        int[] arr = {4,6,8,5,9};
        int[] arr = {4,6,8,5,9,3,2,5,11,45,20,1,7};
//        numK(arr,5);

        topN(arr,4);

    }

    //topN
    public static void topN(int[] arr,int n){
        //1.先取出n个元素放到一个数组中,将这个n个元素构建小顶堆
        int[] arrN = new int[n];
        for(int i = 0; i < n; i ++){
            arrN[i] = arr[i];
        }
        //构造小顶堆
        for(int i = arrN.length / 2 - 1; i >= 0; i --){
            adjustHeap(arrN, i, arrN.length);
            System.out.println("调整"+i+"节点：" + Arrays.toString(arrN));
        }
        //2.遍历剩余的元素，跟小顶堆 堆顶比较，交换，再重新构建
        for(int i = n; i < arr.length; i ++){
            if(arr[i] > arrN[0]){
                int temp = arrN[0];
                arrN[0] = arr[i];
                arr[i] = temp;
                //重新调整
                adjustHeap(arrN,0, n);
            }
        }
        System.out.println("前n大元素小顶堆" + Arrays.toString(arrN));
        int[] heapSortArr = heapSort(arrN);
        System.out.println("前n大元素:" + Arrays.toString(heapSortArr));
    }

    //求第K大元素
    public static void numK(int[] arr, int k){
        int numK = HeapSortTest.heapSort(arr)[k-1];
        System.out.println("第K大元素：" + numK );
    }

    //堆排序，降序，用小顶堆
    public static int[] heapSort(int[] arr){
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第1轮" + Arrays.toString(arr));
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第2轮" + Arrays.toString(arr));

        //1.构造小顶堆
        for(int i = arr.length / 2 - 1; i >= 0; i --){
            adjustHeap(arr, i, arr.length);
            System.out.println("调整"+i+"节点：" + Arrays.toString(arr));
        }
        //2.堆顶元素和末尾元素交换，重新调整堆
        int temp = 0;
        for(int j = arr.length - 1;j > 0; j --){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
        System.out.println("降序排序：" + Arrays.toString(arr));
        return arr;
    }

    /**
     * 将以 i 对应的非叶子节点的局部树调整成小顶堆
     * 包括有子子节点的都会调整成小顶堆
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];//先取出当前元素的值保存到临时变量
        //k = i * 2 + 1 是i节点的左子节点
        //遍历所有左子节点
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1){
            //比较左右子节点找出最小值
            if(k + 1 < length && arr[k] > arr[k + 1]){
                k ++;
            }
            if(arr[k] < temp){//如果最小子节点比父节点小，就交换
                arr[i] = arr[k];//把较大值赋给当前节点
                i = k;//i 指向 K ,继续循环比较
            }else {
                break;//因为到时候是从左到右，从下到上调整，所以这里可以break
            }
        }
        //for循环结束后，我们已经将以i 为父节点的数的最小值，放在了顶上，只是这个局部
        arr[i] = temp;//将temp放到调整后的位置
    }
}
