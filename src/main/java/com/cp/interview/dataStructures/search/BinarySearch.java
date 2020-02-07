package com.cp.interview.dataStructures.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000,1234};
//        int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
//        System.out.println("resIndex=" + resIndex);

        List list = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("List=" + list);

    }

    /**
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return 没有返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 如果右多个相同的值也要返回,这几个相同的值必须是挨着的
     */
    public static List binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return new ArrayList();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            //找到mid值不要马上返回，
            //分别向mid左右俩表扫描，将所有满足findVal的元素下标，加入到集合ArrayList
            List<Integer> resIndexList = new ArrayList<>();
            //向左扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {//退户
                    break;
                }
                //否则，将temp放入到resIndexList
                resIndexList.add(temp);
                System.out.println("temp " + temp);
                temp -= 1;//temp左移
            }
            resIndexList.add(mid);
            System.out.println("mid " + mid);

            //向右扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//退户
                    break;
                }
                //否则，将temp放入到resIndexList
                resIndexList.add(temp);
                System.out.println("temp " + temp);
                temp += 1;//temp右移
            }
            return resIndexList;
        }
    }
}
