package com.hanyu.linkedlist;

import java.util.Stack;

/**
 * @author 李小帅
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        SingleLinkedList linkedList1 = new SingleLinkedList();
        // 创建节点

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(5, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(9, "林冲", "豹子头");

        linkedList1.addByOrder(hero1);
        linkedList1.addByOrder(hero2);
        linkedList1.addByOrder(hero3);
        linkedList1.addByOrder(hero4);



        SingleLinkedList linkedList2 = new SingleLinkedList();
        // 创建节点

        HeroNode hero5 = new HeroNode(2, "刘备", "猖狂");
        HeroNode hero6 = new HeroNode(8, "张飞", "守护我要守护的");
        HeroNode hero7 = new HeroNode(7, "马超", "枪如精龙");
        HeroNode hero8 = new HeroNode(4, "关羽", "啊啊啊");

        linkedList2.addByOrder(hero5);
        linkedList2.addByOrder(hero6);
        linkedList2.addByOrder(hero7);
        linkedList2.addByOrder(hero8);


        HeroNode heroNode = mergeLinkedList(linkedList1.getHead(), linkedList2.getHead());
        System.out.println(heroNode);



    }

    // 方法：获取到单链表的节点的个数（头节点不统计）

    /**
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        // 定义一个赋值遍历,没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表中的倒数第k个结点 【新浪面试题】
    // 1.编写一个方法，接收head,同时接收一个index
    // 2. index 表示倒数第index个节点
    // 3. 先遍历链表，得到链表的长度
    // 4. 得到size后，我们从链表的第一个开始遍历（size-index）个
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 如果链表为空，返回null
        if (head.next == null) {
            return null;
        }
        // 第一次遍历，得到链表的长度
        int size = getLength(head);
        // 第二次遍历size-index位置
        // 校验index
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义赋值变量，使用for循环
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 单链表的反转【腾讯面试题，有点难度】
    public static void reverseLinkedList(HeroNode head) {
     if (head.next==null||head.next.next==null){
         return;
     }
     HeroNode begin = head.next;
     HeroNode end = null;
     HeroNode reverseHead = new HeroNode(0,"","");
     while (begin!=null){
         end = begin.next;
         begin.next = reverseHead.next;
         reverseHead.next = begin;
         begin = end;
     }
     head.next = reverseHead.next;

    }

    // 从尾到头打印单链表 【百度，要求方式1：反向遍历 。 方式2：Stack栈】
    public static void reversePrint(HeroNode head){
        if (head.next==null){
            return;
        }
        // 创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        // 将链表的所有节点压入栈中
        while (cur!=null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    // 合并两个有序的单链表，合并之后的链表依然有序【课后练习.】
    public static HeroNode mergeLinkedList(HeroNode head1,HeroNode head2){
        if (head1.next==null){
            if (head2.next==null){
                return null;
            }else {
                return head2;
            }
        }else {
            if (head2.next==null){
                return head1;
            }else {
                SingleLinkedList linkedList = new SingleLinkedList();
                HeroNode cur = head1.next;
                HeroNode next = null;
                while (cur!=null){
                    next = cur.next;
                    // 此方法会把cur的next给断开，所有需要有next进行保存
                    linkedList.addByOrder(cur);
                    cur = next;
                }
                cur=head2.next;
                while (cur!=null){
                    next = cur.next;
                    linkedList.addByOrder(cur);
                    cur = next;
                }
                return linkedList.getHead();
            }
        }

    }


}

class SingleLinkedList {
    // 初始化头节点,头节点不要动,不存放具体的数据
    private final HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

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

    // 删除节点，根据编号来删除
    public void delete(int no) {
        HeroNode temp = head;
        // 找到需要删除的节点
        boolean flag = false;
        while (true) {
            // 已经到链表的最后
            if (temp.next == null) {
                break;
            }
            // 找到了待删除节点的前一个节点temp
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点%d不存在\n", no);
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
                ", nickName='" + nickName + '\'' +
                ", next=" + next +
                '}';
    }
}