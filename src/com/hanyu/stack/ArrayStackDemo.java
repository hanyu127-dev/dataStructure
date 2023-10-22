package com.hanyu.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("show:显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:添加数据");
            System.out.println("pop:从栈取出数据");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据为"+res);

                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }finally {
                        break;
                    }
                default:
                    System.out.println("没有选项，请重新选择");
                    break;
            }
        }
        System.out.println("程序已退出");
    }
}

// 数组模拟栈
class ArrayStack{
    // 栈的大小
    private int maxSize;
    private int[] stack;
    private int top =-1;
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    private boolean isFull(){
        return top==maxSize -1;
    }

    private boolean isEmpty(){
        return top==-1;
    }

    public void push(int value){
        if (isFull()){
            System.out.println("栈已满，无法放入数据");
            return;
        }
        top++;
        stack[top] =value;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空,没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 显示栈，从栈顶开始显示
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }

        int i = top;
        for (; i >-1 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}