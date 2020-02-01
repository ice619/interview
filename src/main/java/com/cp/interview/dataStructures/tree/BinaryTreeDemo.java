package com.cp.interview.dataStructures.tree;

/**
 * 二叉树
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "卢俊义");
        HeroNode heroNode4 = new HeroNode(4, "林冲");
        HeroNode heroNode5 = new HeroNode(5, "关胜");
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setLeft(heroNode5);
        heroNode3.setRight(heroNode4);
        //创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);

        System.out.println("前序遍历");
        binaryTree.preOrder();//12354
        System.out.println("中序遍历");
        binaryTree.infixOrder();//21534
        System.out.println("后序遍历");
        binaryTree.postOrder();//25431

        System.out.println("前序遍历查找："+ binaryTree.preOrderSearch(5));
        System.out.println("中序遍历查找："+ binaryTree.infixOrderSearch(5));
        System.out.println("后序遍历查找："+ binaryTree.postOrderSearch(5));

        System.out.println("测试删除节点");
        binaryTree.delNode(5);
        System.out.println("删除后：前序遍历");
        binaryTree.preOrder();//12354

    }
}

//定义二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除节点
    public void delNode(int no){
        if(this.root != null){
            //如果只有一个root节点，先判断root是不是要删除的节点
            if(root.getNo() == no){
                root = null;
            }else {
                //递归删除
                root.delNode(no);
            }
        }else {
            System.out.println("二叉树为空,不能删除");
        }
    }
    //前序遍历   所谓 前/中/后 序遍历，就是指父节点在 前/中/后 输出；
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //前序查找
    public HeroNode preOrderSearch(int no){
        HeroNode resNode = null;
        if(this.root != null){
            resNode = this.root.preOrderSearch(no);
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
        return resNode;
    }
    //中序查找
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        if(this.root != null){
            resNode = this.root.infixOrderSearch(no);
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
        return resNode;
    }
    //后序查找
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if(this.root != null){
            resNode = this.root.postOrderSearch(no);
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
        return resNode;
    }
}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    //删除
    public void delNode(int no){
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        //左子树递归删除
        if(this.left != null){
            this.left.delNode(no);
        }
        //右子树递归删除
        if(this.right != null){
            this.right.delNode(no);
        }
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);//输出父节点
        //递归向右子树中序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        //递归向左子树后序遍历
        if(this.left != null){
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);//输出父节点
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        //判断当前节点是不是
        if(this.no == no){
            return this;
        }
        //查左子树
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        //左递归没找到，则继续查右子树
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        //查左子树
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        //判断当前节点是不是
        if(this.no == no){
            return this;
        }
        //查右子树
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        //查左子树
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        //查右子树
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        //判断当前节点是不是
        if(this.no == no){
            return this;
        }
        return resNode;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
