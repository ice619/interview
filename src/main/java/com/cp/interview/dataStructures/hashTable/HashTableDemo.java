package com.cp.interview.dataStructures.hashTable;

import java.util.Scanner;

/**
 * 哈希表
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入要查找的id：");
                    int fId = scanner.nextInt();
                    hashTab.findEmpById(fId);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                        break;
            }
        }
    }
}

//创建HashTab 管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;
    public HashTab(int size){
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //注意！！要分别初始化每个链表
        for(int i = 0; i < size; i ++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工id得到该员工应该添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历哈希表
    public void list(){
        for(int i = 0; i<size; i++){
            empLinkedListArray[i].list();
        }
    }

    //根据id查找雇员
    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp empById = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if(empById != null){
            System.out.printf("在第%d条链表中找到 雇员id = %d\n",(empLinkedListNo+1), id);
        }else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    //编写一个散列函数，使用简单的取模
    public int hashFun(int id){
        return id % size;
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//构建链表
class EmpLinkedList{
    //头指针head指向第一个emp,
    private Emp head;

    //添加雇员到链表
    //假设添加到最后，id自增
    public void add(Emp emp){
        //如果添加第一个雇员
        if(head == null){
            head = emp;
            return;
        }
        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){ //说明到最后一位
                break;
            }
            curEmp = curEmp.next;
        }
        //退出时，直接将emp加入链表，即是最后了
        curEmp.next = emp;
    }

    //遍历链表
    public void list(){
        if(head == null){
            System.out.println("当前链表为空");
            return;
        }
        System.out.print("当前链表信息为：");
        Emp curEmp = head;
        while(true){
            System.out.printf("=> id = %d, name = %s \t",curEmp.id, curEmp.name);
            if(curEmp.next == null){//到最后
                break;
            }
            curEmp = curEmp.next;//后移
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findEmpById(int id){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){//找到
                break;
            }
            if(curEmp.next == null){//遍历完没有找到
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}

