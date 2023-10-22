package com.hanyu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // (30+4)×5-6 对应的后缀表达式就是 30 4 + 5 × 6 -
        // 先定义逆波兰表达式
        String suffixExpresssion = "4 5 x 8 - 600 + 8 2 / +";
        // 思路
        // 1.先将 "3 4 + 5 x 6 - " => 放到ArrayList中
        // 2.将ArrayList 传递给一个方法，遍历ArrayList 配合栈，完成计算
        List<String> rpnList = getListString(suffixExpresssion);
        System.out.println(rpnList);
        System.out.println(calculate(rpnList));

    }

    public static List<String> getListString(String suffixExpresssion){

        String[] strings = suffixExpresssion.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String s : strings) {
            list.add(s);
        }
        return list;

    }

    public static int calculate(List<String> list){
        // 创建栈
        Stack<String> stack = new Stack<>();
        // 遍历list
        for (String item : list) {
            // 正则表达式
            if (item.matches("\\d+")){
                // 入栈
                stack.push(item);
            }else {
                // pop出两个数，并运算，在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1-num2;
                }else if (item.equals("x")){
                    res  = num1 * num2;
                }else if (item.equals("/")){
                    res = num1/num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                // 入栈
                stack.push(Integer.toString(res));

            }
        }
        //
        return Integer.parseInt(stack.pop());

    }

}
