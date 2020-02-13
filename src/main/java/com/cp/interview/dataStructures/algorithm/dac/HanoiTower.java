package com.cp.interview.dataStructures.algorithm.dac;

/**
 * 分治算法 - 汉诺塔问题
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5,'A', 'B', 'C');
    }

    /**
     * 从 a -》 c  ，中间使用 b 塔
     */
    public static void hanoiTower(int num, char a, char b, char c){
        //如果只有一个盘
        if(num == 1){
            System.out.println("第一个盘从" + a + "->" + c);
        }else {
            //如果 n >= 2,都可以看成俩个盘，最下面一个盘，上面所有盘是一个盘
            //1.先把最上面的所有盘 A -> B
            hanoiTower(num - 1, a, c, b);
            //2.把最下面的盘 A - C
            System.out.println("第" + num + "个盘从"+ a + "->" + c);
            //3.把B塔上所有盘从 B-C,移动过程使用到a塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
