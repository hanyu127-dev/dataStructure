package com.hanyu.stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.getTop());

        System.out.println("出栈");
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }
}


class LinkedListStack{
    private Node top = new Node(0);

    private Node cur;
    public void push(int value){
        Node temp = top;
        cur = new Node(value);
        cur.next = temp.next;
        temp.next = cur;
        top.next = temp.next;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，无数据");
        }

        Node temp = top.next;
        top.next = top.next.next;
        return temp.getValue();
    }

    private boolean isEmpty(){
        return top.next==null;
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