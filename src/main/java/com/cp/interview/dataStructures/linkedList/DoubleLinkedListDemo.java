package com.cp.interview.dataStructures.linkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //测试修改节点
        HeroNode2 newHeroNode = new HeroNode2(2, "卢俊义1", "玉麒麟1");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表：");
        doubleLinkedList.list();

        //测试删除节点
        doubleLinkedList.del(2);
        doubleLinkedList.del(5);
        System.out.println("删除后的链表：");
        doubleLinkedList.list();

    }
}

class DoubleLinkedList{
    //初始化一个头节点，头节点不要动
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 删除节点
     * 双向链表可以直接找到要删除的节点，自我删除
     */
    public void del(int no){
        if(head.next == null){
            System.out.println("链表为空，不能删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//标识是否找到待删除节点
        while (true){
            if (temp == null){
                break;
            }
            if(temp.no == no){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;

            if(temp.next != null){
                //这行代码，如果删除最有一个节点会出错,要判断一下
                temp.next.pre = temp.pre;
            }

        }else {
            System.out.printf("没有找到编号 %d 的链表\n", no);
        }
    }
    /**
     * 修改节点，根据no修改
     * 双向链表跟单向链表一样
     */
    public void update(HeroNode2 newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的no
        HeroNode2 temp = head.next;
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

    /**
     * 添加一个节点到双向链表最后
     * @param heroNode
     */
    public void add(HeroNode2 heroNode){
        HeroNode2 temp = head;
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
        heroNode.pre = temp;
    }
    //显示链表
    public void list(){
        if(head.next == null){
            return;
        }
        HeroNode2 temp = head.next;
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

class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next; //指向下一节点
    public HeroNode2 pre; //指向前一个节点

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }



    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
