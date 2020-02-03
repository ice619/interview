package com.cp.interview.dataStructures.tree.binarySortTree;

/**
 * 二叉排序树 （BST）
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环添加节点到二叉排序树
        for(int i = 0; i < arr.length; i ++){
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历二叉树");
        binarySortTree.infixOrder();

        //测试删除叶子节点
        binarySortTree.delNode(2);
        binarySortTree.delNode(7);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("删除节点后");
        binarySortTree.infixOrder();

    }
}

//二叉排序树
class BinarySortTree{
    private Node root;

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