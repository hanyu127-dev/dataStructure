package com.hanyu.stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.getTop());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.getTop());


    }
}


class LinkedListStack{
    private Node top ;

    private Node cur;
    public void push(int value){
        if (top==null){
            top = new Node(value);
            return;
        }
        // 定义一个临时指针temp
        cur = new Node(value);
        cur.next = top;
        top = cur;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，无数据");
        }

        Node temp = top;
        top = top.next;
        return temp.getValue();
    }

    private boolean isEmpty(){
        return top==null;
    }

    public Node getTop() {
        return top;
    }
}


class Node{
    private int value;
    public Node next;

    public Node(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}