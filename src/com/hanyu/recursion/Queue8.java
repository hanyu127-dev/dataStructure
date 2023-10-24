package com.hanyu.recursion;

import java.util.Arrays;

/**
 * 八皇后问题
 * arr[i] = val     val代表第i+1个皇后
 */
public class Queue8 {

    // 定义一共有几个皇后
    int max = 8;
    // 定义数组array,保存皇后存放的位置的结果
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount=0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法\n",count);
        System.out.printf("一共有%d解法冲突",judgeCount);
    }
    // 编写一个方法，放置第n+1个皇后
    public void check(int n){
        // 八个皇后已经放好
        if (n==max){
            print();
            return;
        }

        // 一次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 把当前皇后，放到该行的第1列
            array[n] = i;
            // 放置第n个皇后到第i列时，是否冲突
            if (judge(n)){
                // 接着放第n+1个皇后
                check(n+1);
            }
            judgeCount++;
            // 如果冲突，就会把第n个皇后放到n+1列
        }
    }

    // 查看当我们放置第n个皇后时，去检查该皇后和前面已经摆放的皇后冲突
    public boolean judge(int n){
        for (int i = 0; i < n; i++) {
            // 判断第n个皇后是否和前面的n-1个皇后在同一列
            // 判断第n个皇后是否和第i个皇后是否在同一斜线
            if (array[i]==array[n] ||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }

        }
        return true;
    }

    // 打印数组
    private void print(){
        count++;
        System.out.println("结果为："+Arrays.toString(array));
    }
}

