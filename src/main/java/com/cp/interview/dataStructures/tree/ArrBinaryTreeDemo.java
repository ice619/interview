package com.cp.interview.dataStructures.tree;

/**
 * 顺序存储二叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();//1245367
    }
}

/**
 * 顺序二叉树：第N个元素左子节点为 2*N+1，右子节点为2*N+2
 */
class ArrBinaryTree{
    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder方法
    public void preOrder(){
        this.preOrder(0);
    }
    /**
     * 完成顺序存储二叉树的前序遍历
     * @param index 数组下标
     */
    public void preOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树前序遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左递归遍历
        if((index * 2 + 1) < arr.length){
            preOrder(index * 2 + 1);
        }
        //向右递归遍历
        if((index * 2 + 1) < arr.length){
            preOrder(index * 2 + 2);
        }

    }
}
