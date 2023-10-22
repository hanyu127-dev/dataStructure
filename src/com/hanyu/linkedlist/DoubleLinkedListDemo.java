package com.hanyu.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(5, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(9, "林冲", "豹子头");
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero2);
        linkedList.addByOrder(hero3);

        linkedList.list();


    }

}

// 创建双向链表类
class DoubleLinkedList {
    private final HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    // 遍历双向链表
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;

        while (temp.next != null) {
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println(temp);
    }

    // 添加到最后
    public void add(HeroNode2 node) {
        HeroNode2 temp = head;
        // 获取最后一个node
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    // 按no添加
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
            if (temp.next == null) {
                temp.next = heroNode;
                heroNode.pre = temp;
            } else {

                temp.next.pre = heroNode;
                heroNode.next = temp.next;

                temp.next = heroNode;
                heroNode.pre = temp;

            }
        }

    }

    // 更新节点
    public void update(HeroNode2 node) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点
        HeroNode2 temp = head.next;
        // 表示是否找到该节点
        boolean flag = false;
        while (true) {
            // 表示链表已经遍历结束
            if (temp == null) {
                break;
            }
            if (temp.no == node.no) {
                // 找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            // 找到了
            temp.name = node.name;
            temp.nickName = node.nickName;
        } else {
            System.out.printf("没有找到编号为%d的节点，不能修改\n", node.no);
        }
    }

    // 删除节点
    public void delete(int no) {
        if (head == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        // 找到需要删除的节点
        boolean flag = false;
        while (true) {
            // 已经到链表的最后
            if (temp == null) {
                break;
            }
            // 找到了待删除节点的前一个节点temp
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.pre.next = temp.next;
            // 防止空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }

        } else {
            System.out.printf("要删除的节点%d不存在\n", no);
        }

    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    // 指向下一个节点
    public HeroNode2 next;
    public HeroNode2 pre;

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
                ", pre=" + pre +
                '}';
    }
}
