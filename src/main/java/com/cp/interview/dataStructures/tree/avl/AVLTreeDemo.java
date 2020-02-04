package com.cp.interview.dataStructures.tree.avl;

/**
 * 平衡二叉树（AVL）
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for(int i = 0; i < arr.length; i ++){
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("平衡处理");
        System.out.println("树高度：" + avlTree.getRoot().height());
        System.out.println("左子树高度：" + avlTree.getRoot().leftHeight());
        System.out.println("右子树高度：" + avlTree.getRoot().rightHeight());
    }
}

//
class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的节点
    public Node search(int value){
        if(root == null){
            return null;
        }else {
            return root.search(value);
        }
    }
    //查找要删除节点的父节点
    public Node searchParent(int value){
        if(root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }


    /**
     *  1.返回以node为根节点的二叉排序树的最小节点的值
     *  2.删除node为根节点的二叉排序树的最小节点的值
     * @param node 传入的节点（当做二叉排序树的根节点）
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左子节点，就会找最小值
        while(target.left != null){
            target = target.left;
        }
        //这时，target就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value){
        if(root == null){
            return;
        }else {
            //找到要删除节点
            Node targetNode = search(value);
            //如果么有找到
            if(targetNode == null){
                return;
            }
            //如果发现当前树只要一个节点
            if(root.left == null && root.right == null){
                root = null;
                return;
            }

            //去查找targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的节点时叶子节点
            if(targetNode.left == null && targetNode.right == null){
                //判断tartgetNode是父节点的左还是右子节点
                if(parent.left != null && parent.left.value == value){
                    parent.left = null;
                }else if(parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){
                //删除有俩颗子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else {
                //删除只有一颗子树的节点
                //如果targetNode有左子节点
                if(targetNode.left != null){
                    if(parent != null){
                        //如果targetNode是parent的左子节点
                        if(parent.left.value == value){
                            parent.left = targetNode.left;
                        }else {
                            parent.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }
                }else {
                    //如果要删除的节点有右子节点
                    if(parent != null){
                        //如果targetNode是parent的左子节点
                        if(parent.left.value == value){
                            parent.left = targetNode.right;
                        }else {
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }

            }
        }
    }

    //添加节点
    public void add(Node node){
        if(root == null){
            root = node;
        }else {
            root.add(node);
        }
    }
    //中序遍历
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

}

//节点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }
    //返回右子树高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }

    //返回当前节点的高度，以该节点为根节点的树的高度
    public int height(){
        //选左子树右子树中高度较高的一个
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转
    private void leftRotate(){
        //创建新节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新节点的右子树，设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;
        //把当前节点的左子节点设置成新的节点
        left = newNode;
    }
    //右旋转
    private void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    //查找要删除的节点
    public Node search(int value){
        if(value == this.value){//就是该节点
            return this;
        }else if(value < this.value){
            //向左子树查找
            if(this.left == null){
                return null;
            }
            return this.left.search(value);
        }else {
            if(this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value){
        //判断当前节点是否是
        if((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        }else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if(value < this.value && this.left != null){
                return  this.left.searchParent(value);//向左子树递归查找
            }else if(value >= this.value && this.right != null){
                return this.right.searchParent(value);//向右子树递归查找
            }else {
                return null;//没有找到父节点
            }
        }
    }

    //添加节点
    //递归的形式添加节点，注意需要满足二叉排序树要求
    public void add(Node node){
        if(node == null){
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的值关系
        if(node.value < this.value){
            //如果当前节点左子节点为空
            if(this.left == null){
                this.left = node;
            }else {
                //递归的向左子树添加
                this.left.add(node);
            }
        }else {
            if(this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
        //当添加完一个节点后，如果（右子树的高度 - 左子树高度） > 1
        if(rightHeight() - leftHeight() > 1){
            //如果它右子树的左子树高度大于它右子树的右子树高度
            //先对右子节点右旋转，再对当前节点左旋转
            if(right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else {
                leftRotate(); //左旋转
            }
            return;//必须要
        }
        //当添加完一个节点后，如果（左子树的高度 - 右子树高度） > 1
        if(leftHeight() - rightHeight() > 1){
            //如果当前节点左子树的右子树高度大于它的左子树高度
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对当前节点的左子树进行左旋转
                left.leftRotate();
                rightRotate();//右旋转
            }else {
                rightRotate();//右旋转
            }

        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

