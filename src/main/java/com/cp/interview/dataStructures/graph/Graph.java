package com.cp.interview.dataStructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储对应邻接矩阵
    private int numOfEdges;//表示边的数目
    //记录某个节点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试图创建
        int n = 5;//节点个数
        String vertexs[] = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for(String vertex : vertexs){
            graph.insertVertex(vertex);
        }
        //添加边  AB  AC  BC  BD  BE
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);

        graph.showGraph();

        //测试深度遍历dfs
//        System.out.println("深度遍历");
//        graph.dfs();
//        System.out.println();

        //广度优先
        System.out.println("广度优先");
        graph.bfs();
    }

    //构造器 n顶点数目
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        isVisited = new boolean[5];
    }

    //得到第一个邻接节点下标
    public int getFirstNeighbor(int index){
        for(int j = 0; j < vertexList.size(); j++){
            if(edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接节点的下标 来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2){
        for(int j = v2 + 1; j < vertexList.size(); j++){
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i 第一次是0
    private void dfs(boolean[] isVisited, int i){
        //首先访问该节点
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设置为已访问
        isVisited[i] = true;
        //查找i的第一个邻接节点
        int w = getFirstNeighbor(i);
        while(w != -1){
            if(!isVisited[w]){
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载,遍历所有节点，并进行dfs
    public void dfs(){
        //遍历所有节点，进行dfs
        for(int i = 0; i < getNumOfVertex(); i ++){
            if(!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    //对一个节点进行广度优先遍历
    private void bfs(boolean[] isVisited, int i){
        int u;//表示队列头节点对应下标
        int w;//邻接点w
        //队列,记录节点访问顺序
        LinkedList queue = new LinkedList();
        //访问节点
        System.out.print(getValueByIndex(i) + "=>");
        //标记已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()){
            //取出队列头节点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接点的下标W
            w = getFirstNeighbor(u);
            while (w != -1){//找到
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //以u为前一个节点，找w后面的下一个邻接点
                w = getNextNeighbor(u, w);//体现出广度优先
            }
        }
    }
    //遍历所有节点 都进行广度优先搜索
    public void bfs(){
        for(int i = 0; i < getNumOfVertex(); i ++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //返回节点个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边的数据
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点i（下标） 对的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1 和 v2的权值
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link : edges ){
            System.out.println(Arrays.toString(link));
        }
    }

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    /**
     * 添加边
     * @param v1 第一个顶点下标，第几个顶点
     * @param v2 第二个顶点下标
     * @param weight
     */
    public void insertEdges(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
