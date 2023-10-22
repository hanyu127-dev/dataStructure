package com.hanyu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 完成将中缀表达式转后缀表达式
        // 说明
        // 1. 将中缀表达式“1+((2+3)×4)-5”转为"1 2 3 + 4 × + 5 –"
        // 2. 因为直接对字符串进行操作，不方便，因此，先将表达式存到list中
        String expression = "1+ (( 2+ 3) *4)-5";
        System.out.println("中缀表达式对应的list:");
        System.out.println(toList(expression));
        List<String> infixToSuffix = infixToSuffix(toList(expression));
        System.out.println("后缀表达式对应的list:");
        System.out.println(infixToSuffix);


        // (30+4)×5-6 对应的后缀表达式就是 30 4 + 5 × 6 -
        // 先定义逆波兰表达式
//        String suffixExpresssion = "4 5 x 8 - 600 + 8 2 / +";
//        // 思路
//        // 1.先将 "3 4 + 5 x 6 - " => 放到ArrayList中
//        // 2.将ArrayList 传递给一个方法，遍历ArrayList 配合栈，完成计算
//        List<String> rpnList = getListString(suffixExpresssion);
//        System.out.println(rpnList);
//        System.out.println(calculate(rpnList));

    }

    public static List<String> getListString(String suffixExpresssion) {

        String[] strings = suffixExpresssion.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String s : strings) {
            list.add(s);
        }
        return list;

    }

    public static int calculate(List<String> list) {
        // 创建栈
        Stack<String> stack = new Stack<>();
        // 遍历list
        for (String item : list) {
            // 正则表达式
            if (item.matches("\\d+")) {
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数，并运算，在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("x")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                // 入栈
                stack.push(Integer.toString(res));

            }
        }
        //
        return Integer.parseInt(stack.pop());

    }

    // 将中缀表达式装为对应的list
    public static List<String> toList(String expression) {
        List<String> ls = new ArrayList<>();
        // 遍历字符串
        int i = 0;
        // 多位数拼接
        String str;
        //每遍历一个字符，就放入c中
        char c;
        do {
            // 如果为空格，则继续
            if ((c = expression.charAt(i)) == ' ') {
                i++;
                continue;
            }
            // 如果为运算符或空格，
            if ((c = expression.charAt(i)) < 48 || (c = expression.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                //先将str置空
                str = "";
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < expression.length());
        return ls;
    }

    // 将中缀表达式转为后缀表达式对应的list
    public static List<String> infixToSuffix(List<String> strs) {
        // 定义两个栈，s1为符号栈,s2存储中间结果
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        for (String item : strs) {

            // 如果是数
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                // 左括号直接入s1
                s1.push(item);
            } else if (")".equals(item)) {
                // 右括号则弹出放到s2
                while (!("(").equals(s1.peek())) {
                   s2.add(s1.pop());
                }
                // 将左括号弹出栈
                s1.pop();
            } else {
                // 当item的优先级小于等于s1栈顶的运算符，将s1栈顶运算符弹出并放入s2中
                while (s1.size() != 0 &&getValue(s1.peek())>=getValue(item)){
                    s2.add(s1.pop());
                }
                // 将item压入s1中
                s1.push(item);
            }
        }

        // 将s1中的数据加入s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }

    public static int getValue(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = 1;
                break;
            case "-":
                res = 1;
                break;
            case "*":
                res = 2;
                break;
            case "/":
                res = 2;
                break;
            default:
                break;
        }
        return res;

    }

}
