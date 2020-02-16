package com.cp.interview.dataStructures.algorithm.prim;

import java.util.Arrays;

/**
 * 普利姆算法（prim）（修路问题） 求最小生成树（MST）
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //用二维数组表示邻接矩阵的关系，10000这个大数表示俩个点不连通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };

        MGraph graph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        minTree.showGraph(graph);

        //测试prim算法
        minTree.prim(graph, 0);
    }

}

//创建最小生成树
class MinTree {
    /**
     * @param graph  图
     * @param verxs  图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }
    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for(int[] link: graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * prim算法，得到最小生成树
     * @param graph
     * @param v 表示图从第几个顶点开始生成
     */
    public void prim(MGraph graph, int v){
        //visited 标记顶点是否被访问过，默认0表示没有访问过
        int[] visited = new int[graph.verxs];
        //把当前节点标记为已访问
        visited[v] = 1;
        //h1 h2记录俩个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//将minWeight初始成一个大数，后面遍历过程会替换
        for(int k = 1; k < graph.verxs; k ++){
            //这个是确定每次生成的子图，和哪个节点的举例最近
            for(int i = 0; i < graph.verxs; i ++){//i表示被访问过的节点
                for(int j = 0; j < graph.verxs; j ++){//j表示没有被访问过的节点
                    if(visited[i] == 1 && visited[j] ==0 && graph.weight[i][j] < minWeight){
                        //寻找已访问和未访问节点权值最小的边
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">  权值：" + minWeight);
            //将当前这个节点标记为已经访问
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs;//表示图节点个数
    char[] data;//存放节点名称
    int[][] weight;//存放边，邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
