package com.hanyu.linkedlist;

public class Josephu {
    public static void main(String[] args) {

        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(125);
        linkedList.showBoy();

        System.out.println("测试小孩出圈");
        linkedList.countBoy(10,20,125);

    }
}

// 创建环形链表
class CircleSingleLinkedList {
    //创建first节点
    private Boy first;

    // 添加小孩节点
    public void addBoy(int nums) {
        // nums作数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        // 辅助指针
        Boy curBoy = null;

        for (int i = 1; i <= nums; i++) {
            // 根据编号创建小孩节点
            Boy boy = new Boy(i);
            // 加入节点
            if (i == 1) {
                first = boy;
                // 构成环状
                first.setNext(first);
                // 让curBoy指向第一个小孩
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }

        }
    }

    // 遍历环形链表
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (!curBoy.getNext().equals(first)) {
            System.out.printf("小孩的编号为%d\n", curBoy.getNo());
            curBoy = curBoy.getNext();
        }
    }

    public void countBoy(int startNo, int k, int m) {
        if (first.getNext() == null || startNo < 1 || startNo > m) {
            System.out.println("参数输入有误，请重新输入~~~~");
            return;
        }

        // 创建辅助指针
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 移动指针
        for (int i = 0; i < startNo-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // 出圈操作
        while (true){
            if (helper==first){
                // 圈中只有一个节点
                break;
            }
            // 让first和helper移动
            for (int i = 0; i < k - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // first出圈
            System.out.printf("小孩%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);

        }

        System.out.printf("最后留在圈中小孩编号%d\n",first.getNo());

    }

}


// 创建boy类，表示节点类
class Boy {
    private int no;
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
