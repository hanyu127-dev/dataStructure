package com.hanyu.hashtab;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        //写一个简单菜单来测试
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        int id;
        while (flag) {
            System.out.println("add:添加雇员");
            System.out.println("find:查找雇员");
            System.out.println("list:显示雇员");
            System.out.println("exit:退出程序");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id");
                    id = scanner.nextInt();
                    System.out.println("请输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "exit":
                    scanner.close();
                    flag = false;
                    break;
                default:
                    break;
            }
        }
    }
}

// 表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

// 表示一条链表
class EmpLinkedList {
    // 头指针，指向第一个Emp
    public Emp head;

    // 添加雇员
    // 说明
    // 1. 假定，每添加雇员时，id是自增的，即id的分配总是从小到大的
    // 即是添加到链表的最后
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        // 定义一个指针
        Emp cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        // 加到最后即可
        cur.next = emp;
    }


    public void list(int i) {
        if (head == null) {
            System.out.println("链表" + (i + 1) + "为空");
            return;
        }
        System.out.print("链表" + (i + 1) + "的信息为");
        // 辅助指针
        Emp cur = head;
        while (cur != null) {
            System.out.printf("=> id=%d name=%s\t", cur.id, cur.name);
            cur = cur.next;
        }


    }

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp cur = head;
        while (true) {
            if (cur.id == id) {
                break;
            }
            if (cur.next == null) {
                cur = null;
                break;
            }
            cur = cur.next;
        }

        return cur;
    }
}

// 创建哈希表
class HashTab {
    private EmpLinkedList[] array;
    private int size;

    public HashTab(int size) {
        // 初始化
        array = new EmpLinkedList[size];
        this.size = size;
        // 留了个坑
        // EmpLinkedList为null
        // 分别初始化
        for (int i = 0; i < size; i++) {
            array[i] = new EmpLinkedList();
        }

    }

    public void add(Emp emp) {
        // 根据员工id选择添加的数组进行添加
        int index = hashFun(emp.id);
        array[index].add(emp);

    }
    // 编写一个散列函数

    public int hashFun(int id) {
        return id % size;
    }

    // 遍历所有的数组
    public void list() {
        for (int i = 0; i < array.length; i++) {
            array[i].list(i);
        }
    }

    public void findById(int id) {
        int index = hashFun(id);
        Emp emp = array[index].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表找到id为%d的雇员%s\n", index, id, emp.toString());
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }
}

