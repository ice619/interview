package com.cp.interview.dataStructures.linkedList;

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
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
//        singleLinkedList.list();

        //添加时考虑编号顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();

        //测试修改节点
        HeroNode newHeroNode = new HeroNode(2, "卢俊义1", "玉麒麟1");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表：");
        singleLinkedList.list();

        //测试删除节点
        singleLinkedList.del(2);
        singleLinkedList.del(5);
        System.out.println("删除后的链表：");
        singleLinkedList.list();

    }
}

class SingleLinkedList{
    //初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向链表
    //当不考虑编号顺序时
    //1.找到当前链表最后节点
    //2.将最后节点next 指向新节点
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

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
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


