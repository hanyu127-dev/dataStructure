package com.hanyu.queue;

import java.util.Scanner;

/**
 * @author 李小帅
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        // 测试
        ArrayQueue queue = new ArrayQueue(3);
        // 接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    try {
                        System.out.println("请输入一个数:");
                        int value = scanner.nextInt();
                        queue.addQueue(value);
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("头数据为%d\n",res);
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }

}


class ArrayQueue{
    // 表示数组的最大容量
    private int maxSize;
    // 队列头
    private int front;
    // 队列尾
    private int rear;
    // 模拟队列的数组
    private int[] arr;

    // 队列构造器
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        // 指向队列头部的前一个位置
        front = -1;
        // 指向队列尾部的数据，就是队列的最后一个数据
        rear = -1;
    }
    // 判断队列是否已满
    public boolean isFull(){
        return rear == maxSize-1;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满，不能加入队列");
        }
        rear++;
        arr[rear] = n;
    }
    // 数组出队列
    public int getQueue(){
        if (isEmpty()){
            // 通过抛出异常来处理
            throw new RuntimeException("队列为空，不能数据");
        }
        front++;
        return arr[front];
    }

    // 显示队列所有数据
    public void showQueue(){
        // 遍历
        if (isEmpty()){
            System.out.println("队列为空，没有数组");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }

    }

    // 显示队列的头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front+1];
    }

}
