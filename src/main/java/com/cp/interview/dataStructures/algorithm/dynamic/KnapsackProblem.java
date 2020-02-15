package com.cp.interview.dataStructures.algorithm.dynamic;

/**
 * 动态规划- 背包问题
 * 填表推导
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品重量
        int[] val = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包容量
        int n = val.length;//物品的个数

        //v[i][j] 表示在前i个武平中能装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //为了记录放入商品情况，定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行第一列，可以不去处理，因为默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//第一列设置为0；
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//第一行设置为0
        }

        //
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j = 1; j < v[0].length; j++) {//不处理第一列
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                   // v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放情况
                    if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]){
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //输出最后放入的哪些商品
        //遍历path,这样输出会把所有放入情况都得到，右冗余
//        for(int i = 0; i < path.length; i ++){
//            for(int j = 0; j < path[i].length; j ++){
//                if(path[i][j] == 1){
//                    System.out.println("第"+i+"个商品放入到背包");
//                }
//
//            }
//        }

        //输出
        int i = path.length - 1;//行最大下标
        int j = path[0].length - 1;//列的最大下标
        while(i > 0 && j > 0){//从后往前遍历
            if(path[i][j] == 1){
                System.out.println("第"+i+"个商品放入到背包");
                j -= w[i - 1];//w[i]
            }
            i--;
        }
        for (int s = 0; s < path.length; s++) {
            for (int l = 0; l < path[s].length; l++) {
                System.out.print(path[s][l] + " ");
            }
            System.out.println();
        }
    }
}
