package com.cp.interview.dataStructures.algorithm.kruskal;

import java.util.Arrays;

/**
 * 克鲁斯卡尔((Kruskal)算法 求最小生成树  公交站问题（跟修路一样）
 */
public class KruskalCase {
    private int edgeNum;//边个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF表示俩个顶点不连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int matrix[][] = {
                {0,    12, INF, INF, INF, 16,  14,},
                {12,   0,   10, INF, INF,  7,  INF,},
                {INF, 10,   0,   3,  5,    6,  INF,},
                {INF, INF,  3,  0,   4,   INF, INF,},
                {INF, INF,  5,  4,   0,   2,    8,},
                {16,   7,    6,  INF, 2,   0,   9,},
                {14,   INF, INF, INF, 8,   9,   0,}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();

        kruskalCase.kruskal();

    }

    public KruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边个数
        int vlen = vertexs.length;
        //初始化顶点，复制拷贝方式
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边，使用复制拷贝方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal(){
        int index = 0;//表示最后结果数组的索引
        //用于保存“已有最小生成树” 中每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
        //创建结果数组，保存最后最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中 所有边的集合
        EData[] edges = getEdges();
        System.out.println("获取图的边集合=" + Arrays.toString(edges));

        //按照边权值大小排序
        sortEdges(edges);

        //遍历edges,将边添加到最小生成树中时，判断是否构成回路，
        for(int i = 0; i < edgeNum; i ++){
            //获取到第i条边的第一个顶点（七点）,第二个顶点
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            //获取p1在已有的最小生成树的终点
            int m = getEnd(ends, p1);
            //获取p2在已有的最小生成树的终点
            int n = getEnd(ends, p2);

            //是否构成回路
            if(m != n){//没有构成回路
                ends[m] = n;//设置m在已有最小生成树中的终点
                rets[index++] = edges[i];//有一条边加入rets数组
            }
        }
        //输出
        System.out.println("最小生成树=");
        for(int i = 0; i < index; i ++){
            System.out.println(rets[i]);
        }
//        System.out.println("最小生成树=" + Arrays.toString(rets));
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%13d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //边按权值从小到大排序  冒泡
    private void sortEdges(EData[] edges){
        for(int i = 0; i < edges.length - 1; i ++){
            for(int j = 0; j < edges.length - 1 -i; j ++){
                if(edges[j].weight > edges[j + 1].weight){
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * @param ch  顶点的值 比如‘A’
     * @return 返回ch顶点资源的下表，找不到返回-1
     */
    private int getPosition(char ch){
        for(int i = 0; i < vertexs.length; i ++){
            if(vertexs[i] == ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，放到EData[] 数组中，后面需要遍历
     * 通过matrix邻接矩阵获取
     * EData[]  形式[['A', 'B', 12],[],...]
     * @return
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for(int i = 0; i < vertexs.length; i ++){
            for(int j = i + 1; j < vertexs.length; j ++){
                if(matrix[i][j] != INF){
                    edges[index ++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断俩个顶点终点是否相同
     * @param ends 数组记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中逐步形成的
     * @param i 表示传入的顶点对应的下标
     * @return 返回下标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i){
        while(ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

}

//创建一个边类
class EData{
    char start;//边一个点
    char end;//边另一个点
    int weight;//边权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}