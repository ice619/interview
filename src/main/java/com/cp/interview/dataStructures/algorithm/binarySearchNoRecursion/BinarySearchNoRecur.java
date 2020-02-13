package com.cp.interview.dataStructures.algorithm.binarySearchNoRecursion;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearch(arr, 67);
        System.out.println("index=" + index);
    }

    //二分查找非递归实现
    //arr 是升序排列
    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){//继续查找
            int mid = (left + right) / 2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid - 1;//向左边
            }else {
                left = mid + 1;//向右边
            }
        }
        return -1;
    }
}
