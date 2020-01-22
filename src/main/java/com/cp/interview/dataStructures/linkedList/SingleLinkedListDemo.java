package com.cp.interview.dataStructures.linkedList;

import java.util.Stack;

/**
 * 单向链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        s56ingleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
//        singleLinkedList.list();

        //添加时考虑编号顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();

        //测试修改节点
//        HeroNode newHeroNode = new HeroNode(2, "卢俊义1", "玉麒麟1");
//        singleLinkedList.update(newHeroNode);
//        System.out.println("修改后的链表：");
//        singleLinkedList.list();

        //测试删除节点
//        singleLinkedList.del(2);
//        singleLinkedList.del(5);
//        System.out.println("删除后的链表：");
//        singleLinkedList.list();

        //获取链表节点个数
        int length = getLength(singleLinkedList.getHead());
        System.out.println("节点长度为：" + length);

        //测试查找单链表中倒数第 K 个节点
//        HeroNode lastIndexNode = findLastIndexNode(singleLinkedList.getHead(), 2);
//        System.out.println("倒数第K个节点" + lastIndexNode );

        //测试将单链表反转
//        System.out.println("---测试将单链表反转---");
//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();

        //反转打印单链表
        System.out.println("----反转打印单链表----");
        reversePrint(singleLinkedList.getHead());


    }

    /**
     * 获取单链表节点个数，（如果带头节点，则不包含头节点）
     * @param head 链表的头节点
     * @return 返回节点个数
     */
    public static int getLength(HeroNode head){
        if(head.next == null){
            System.out.println("链表为空");
            return 0;
        }
        int count = 0;
        HeroNode temp = head.next;
        while (temp != null){
            count ++;
            temp = temp.next;
        }
        return count;
    }

    /**
     * 查找单链表中倒数第 K 个节点
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        if(head.next == null){
            System.out.println("链表为空");
            return null;
        }
        //第一次遍历
        int size = getLength(head);
        //第二次遍历 找size - index 位置，就是倒数第K个节点
        if(index <= 0 || index > size){
            return null;
        }
        HeroNode temp = head.next;
        for(int i = 0; i < size - index; i ++){
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 将单链表反转
     * @param head
     */
    public static void reverseList(HeroNode head){
        if(head.next == null || head.next.next == null){
            System.out.println("单链表为空或者单链表只有一个节点，无需反转");
            return;
        }

        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode cur = head.next;
        HeroNode next = null;
        while (cur != null){
            next = cur.next;
            //将取出即将要插入新链表最前端的节点的next先指向当前在最前的节点
            cur.next = reverseHead.next;
            //将取出的节点插入新链表最前端
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * 从尾到头逆序打印单链表
     * 利用栈先进后出特点
     * @param head
     */
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;
        }
        HeroNode cur = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }

    }

}

class SingleLinkedList{
    //初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    /**
     * //添加节点到单向链表
     *     //当不考虑编号顺序时
     *     //1.找到当前链表最后节点
     *     //2.将最后节点next 指向新节点
     * @param heroNode
     */
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true){
            //找到最后
            if(temp.next == null){
                break;
            }
            //如果没有找到，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表最后
        temp.next = heroNode;
    }

    /**
     * //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在
        while(true){
            if(temp.next == null){//说明temp已经在链表最后
                break;
            }
            if(temp.next.no > heroNode.no){//位置找到，就在temp后面插入
                break;

            }else if(temp.next.no == heroNode.no){//编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;//后移
        }

        if(flag){//编号存在
            System.out.printf("要插入的英雄的编号 %d 已经存在了，不能添加.\n", heroNode.no);
        }else {
            //插入
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点，根据no修改
    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的no
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到
        while (true){
            if(temp == null){
                break;//已经遍历完
            }
            if(temp.no == newHeroNode.no){
                flag = true;//找到了
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            System.out.printf("没找找到编号 %d 的节点\n", newHeroNode.no);
        }
    }

    //删除节点
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;//标识是否找到待删除节点
        while (true){
            if (temp.next == null){
                break;
            }
            if(temp.next.no == no){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("没有找到编号 %d 的链表\n", no);
        }
    }

    //显示链表
    public void list(){
        if(head.next == null){
            return;
        }
        HeroNode temp = head.next;
        while (true){
            System.out.println(temp);
            //判断是否到了链表最后
            if(temp.next == null){
                break;
            }
            //temp 后移
            temp = temp.next;
        }
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; //指向下一节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }



    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}


