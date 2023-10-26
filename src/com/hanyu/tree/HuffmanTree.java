package com.hanyu.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 赫夫曼树
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);


    }

    // 编写一个前序遍历的方法
    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("是空树，不能遍历");
        }
    }

    // 创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        // 第一步为了操作方便
        // 1.遍历arr数组
        // 2.将arr的每个元素构建成一个Node
        // 3. 将Node 放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        // 排序 从小到达排序
        Collections.sort(nodes);


        // 循环处理即可

        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);

            // 获取前两个元素
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            // 构建一个二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 移除集合中的已经使用的元素
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 添加新构建的二叉树
            nodes.add(parent);
        }
        return nodes.get(0);

        // 返回集合中的唯一元素

//        // 取出根节点最小的节点（二叉树）
//        Node left = nodes.get(0);
//        Node right = nodes.get(1);
//
//        Node parent = new Node(left.value+right.value);
//        parent.left = left;
//        parent.right =right;
//
//        // 从list中删除处理过的二叉树
//        nodes.remove(left);
//        nodes.remove(right);
//
//        // 添加新的node
//        nodes.add(parent);
//        // 排序
//        Collections.sort(nodes);
//        // 到此为止，第一次处理完毕
//        System.out.println(nodes);
//
//

    }
}

// 创建节点类
// 为了支持排序，实现Comparable接口
class Node implements Comparable<Node> {
    // 节点权值
    public int value;
    // 左子节点
    Node left;
    // 右子节点
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 前序遍历

    public void  preOrder(){
        System.out.println(this);

        if (this.left!=null){
            this.left.preOrder();
        }

        if (this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 表示从小到达排序
        return this.value - o.value;
    }
}