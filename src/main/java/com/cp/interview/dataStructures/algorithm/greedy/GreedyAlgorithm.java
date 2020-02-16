package com.cp.interview.dataStructures.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法（贪婪算法）（集合覆盖问题）
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台  hashSet不能重复
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //存放结果电台集合
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时集合，保存在遍历过程中电台覆盖的地区和当前还没有覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //maxKey保存在一次遍历过程中能覆盖最多未覆盖地区对应的电台的key
        String maxKey = null;
        while(allAreas.size() != 0){
            maxKey = null;
            for(String key : broadcasts.keySet()){
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet 和 allAreas 集合的交集，交集赋给tempSet
                tempSet.retainAll(allAreas);
                //如果tempSet包含的数量比当前maxkey指向的集合地区还多
                //就将maxkey 指向当前key
                //tempSet.size() > broadcasts.get(maxKey).size() 体现出贪心算法，每次都选最优的
                if(tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if(maxKey != null){
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的结果" + selects);

    }
}
