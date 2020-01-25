package com.cp.interview.dataStructures.linkedList;

/**
 * 环形链表  约瑟夫环问题（丢手帕）
 */
public class Josepfu {
    public static void main(String[] args) {
        //测试，构建遍历环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);
        circleSingleLinkedList.showBoy();

        //测试小孩儿出圈
        circleSingleLinkedList.countBoy(10,20,125);
    }
}

/**
 * 创建一个环形单向链表
 */
class CircleSingleLinkedList{
    private Boy first = new Boy(-1);

    //添加小孩节点
    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针
        for(int i = 1; i <= nums; i++){
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历
    public void showBoy(){
        if(first == null){
            System.out.println("没有任何小孩儿");
            return;
        }
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩编号%d \n",curBoy.getNo());
            if(curBoy.getNext() == first){//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//curBoy 后移

        }
    }

    /**
     * 根据用户输入，计算出小孩儿出圈顺序
     * @param startNo 从第几个小孩儿开始数数
     * @param countNum 数几下
     * @param nums 最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums){
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //1.创建一个辅助指针变量，指向环形链表最后一个节点
        Boy helper = first;
        while(true){
            if(helper.getNext() == first){//说明helper指向最后节点
                break;
            }
            helper = helper.getNext();
        }
        //2.小孩儿报数前，先让first 和 helper 移动 startNo - 1 次
        for(int i = 0; i < startNo - 1 ; i ++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //3.当报数时，让first 和 helper 移动 countNum - 1 次,然后出圈
        //循环操作，直到只有一个节点
        while(true){
            if(helper == first){
                //剩余一个节点
                System.out.println("最后一个节点" + first.getNo());
                break;
            }
            for(int i = 0; i < countNum - 1 ; i ++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时first 指向的节点就是要出圈的节点
            System.out.println("出圈：" + first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
    }
}

class Boy {
    private int no;//编号
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
