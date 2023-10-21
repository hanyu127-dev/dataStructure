package com.hanyu.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        SingleLinkedList linkedList = new SingleLinkedList();
        // 创建节点

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(2, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero2);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero4);

        linkedList.list();
        linkedList.addByOrder(hero4);

    }
}

class SingleLinkedList {
    // 初始化头节点,头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    // 第一种方法在添加英雄时，直接添加到链表的尾部
    public void add(HeroNode heroNode) {
        if (head.next == null) {
            head.next = heroNode;
            return;
        }
        HeroNode temp = head.next;
        // 遍历列表
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        // 标注添加的编号是否存在，默认为false
        boolean flag = false;
        while (true) {
            // temp 已经在链表的最后
            if (temp.next == null) {
                break;
            }
            // 在temp的后面添加
            if (temp.next.no > heroNode.no) {

            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;

        }
        // 不能添加
        if (flag) {
            System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }


    // 显示链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (temp.next != null) {
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println(temp);
    }
}


// 第一HeroNode,每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    // 指向下一个节点
    public HeroNode next;

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
                ", nickName='" + nickName +
                '}';
    }
}