package com.cp.interview.dataStructures.tree.threadedBinaryTree;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化二叉树
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试结果 查看10号节点
        HeroNode left = node5.getLeft();
        System.out.println("10号前驱节点："+ left);

        System.out.println("使用线索化方式 遍历线索化二叉树");
        threadedBinaryTree.threadedList();

    }
}

//线索化后的二叉树
class ThreadedBinaryTree{
    private HeroNode root;
    //指向前驱节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载线索化方法
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //遍历线索化二叉树
    public void threadedList(){
        HeroNode node = root;
        while(node != null){
            //循环找到leftType == 1 的节点
            //后面随着遍历会变化，因为当leftType == 1时，说明该节点时按照线索化处理后的有效节点
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点右指针指向后继节点，就一直输出
            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    /**
     * 中序线索化
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node){
        if(node == null){
            return;
        }
        //1.线索化左子树
        threadedNodes(node.getLeft());

        //2.线索化当前节点
        //左右子树为空时才处理，因为线索化就是要利用这些空的指针
        //2.1处理当前节点的前驱节点
        if(node.getLeft() == null){
            //让当前节点左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点左指针类型，指向前驱节点
            node.setLeftType(1);
        }
        //2.2处理后继节点，针对下一次要处理的时候
        if(pre != null && pre.getRight() == null){
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //!!每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        //3.线索化右子树
        threadedNodes(node.getRight());

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

    //leftType==0.表示指向左子树，1 表示指向前驱节点
    //leftType==0.表示指向右子树，1 表示指向后继节点
    private int leftType;
    private int rightType;

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
