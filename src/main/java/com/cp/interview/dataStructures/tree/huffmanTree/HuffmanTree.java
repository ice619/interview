package com.cp.interview.dataStructures.tree.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,6,1};
        Node root = creatHuffmanTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else {
            System.out.println("空树不能遍历");
        }
    }

    //创建赫夫曼树
    public static Node creatHuffmanTree(int arr[]){
        List<Node> nodes = new ArrayList<>();
        for(int value: arr){
            nodes.add(new Node(value));
        }

        while(nodes.size() > 1){
            //排序,从小到大
            Collections.sort(nodes);
//        System.out.println(nodes);
            //取出根节点权值最小俩颗二叉树（可以看做左右指针都为null的二叉树）
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //构建一个新二叉树
            Node parent = new Node(leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //从arrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将parent加入到nodes
            nodes.add(parent);
        }
        //返回root节点
        return nodes.get(0);
    }

}

class Node implements Comparable<Node>{
    int weight;//权值
    Node left;
    Node right;

    public Node(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}
