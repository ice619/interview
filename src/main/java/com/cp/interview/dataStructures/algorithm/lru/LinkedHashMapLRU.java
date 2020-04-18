package com.cp.interview.dataStructures.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap实现LRU算法
 * accessOrder 为true：按照访问顺序排序，false：按照插入顺序排序(默认)
 * 当accessOrder为true时，get方法和put方法都会调用recordAccess方法使得最近使用的Entry移到双向链表的末尾
 */
public class LinkedHashMapLRU {
    public static void main(String[] args) {
        final int maxSize = 10;
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(0, 0.75f, true){
            //通过removeEldestEntry(eldest)判断是否需要删除最老的Entry
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > maxSize;
            }
        };
        System.out.println(map.size());
        for (int i = 0; i < 10; i ++){
            map.put(i, i);
        }
        System.out.println("当前数据：" + map.toString());
        System.out.println(map.get(6));
        System.out.println("访问数据之后：" + map.toString());
        map.put(11,11);
        System.out.println("插入11条数据之后" + map.toString());
    }
}

//测试LinkedHashMap使用
class LinkedHashMapTest{
    public static void main(String[] args) {
        System.out.println("测试按插入顺序排序：");
        //accessOrder 为true：按照访问顺序排序，false：按照插入顺序排序
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < 10; i ++){
            map.put(i, i);
        }
        System.out.println(map.toString());
        System.out.println(map.get(6));
        System.out.println(map.toString());
        System.out.println("测试按插入顺序排序 结束");

        System.out.println("------------------------------");
        System.out.println("测试按访问顺序排序：");
        //accessOrder 为true：按照访问顺序排序，false：按照插入顺序排序
        LinkedHashMap<Integer, Integer> map1 = new LinkedHashMap<>(0, 0.75f, true);
        for (int i = 0; i < 10; i ++){
            map1.put(i, i);
        }
        System.out.println(map1.toString());
        System.out.println(map1.get(6));
        System.out.println(map1.get(2));
        System.out.println(map1.get(2));
        System.out.println(map1.get(2));
        System.out.println(map1.get(6));
        System.out.println(map1.toString());
        System.out.println("测试按访问顺序排序 结束");
    }
}
