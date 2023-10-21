package com.hanyu.linkedlist;

/**
 * @author 李小帅
 */
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

        // 修改之前显示节点
        linkedList.list();

        HeroNode newHeroNode = new HeroNode(3, "小卢", "玉麒麟~~");
        linkedList.update(newHeroNode);

        System.out.println("");

        // 修改之后显示节点
        linkedList.list();

    }
}

class SingleLinkedList {
    // 初始化头节点,头节点不要动,不存放具体的数据
    private final HeroNode head = new HeroNode(0, "", "");

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
                break;
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

    // 修改节点的信息，根据编号来修改
    public void update(HeroNode newHeroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点
        HeroNode temp = head.next;
        // 表示是否找到该节点
        boolean flag = false;
        while (true) {
            // 表示链表已经遍历结束
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                // 找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            // 找到了
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号为%d的节点，不能修改\n", newHeroNode.no);
        }
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