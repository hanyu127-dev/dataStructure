package com.hanyu.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        tree.postOrder(0);

    }
}

// 编写一个ArrayBinaryTree,实现顺序存储二叉树遍历
class ArrayBinaryTree{
    // 存储数据节点的数组
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }
    // index：数组的下标
    public void preOrder(int index){
        // 如果数组为空
        if (arr==null||arr.length ==0){
            System.out.println("数组为空，不能按照二叉树的前序进行遍历");
            return;
        }
        // 输出当前的元素
        System.out.printf(arr[index]+"\t");
        // 向左递归
        if (index*2+1<arr.length){
            preOrder(index*2+1);
        }
        // 向右递归
        if (index*2+2<arr.length){
            preOrder(index*2+2);
        }
    }

    // 中序遍历
    public void infixOrder(int index){
        // 如果数组为空
        if (arr==null||arr.length ==0){
            System.out.println("数组为空，不能按照二叉树的前序进行遍历");
            return;
        }

        // 向左递归
        if (index*2+1<arr.length){
            infixOrder(index*2+1);
        }

        // 输出当前的元素
        System.out.printf(arr[index]+"\t");


        // 向右递归
        if (index*2+2<arr.length){
            infixOrder(index*2+2);
        }
    }

    public void postOrder(int index){
        // 如果数组为空
        if (arr==null||arr.length ==0){
            System.out.println("数组为空，不能按照二叉树的前序进行遍历");
            return;
        }

        // 向左递归
        if (index*2+1<arr.length){
            postOrder(index*2+1);
        }

        // 向右递归
        if (index*2+2<arr.length){
            postOrder(index*2+2);
        }

        // 输出当前的元素
        System.out.printf(arr[index]+"\t");
    }
}
