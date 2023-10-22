package com.hanyu.stack;

public class Calculator {
    public static void main(String[] args) {
        // 1. 扫描表达式
        String expression = "8/2+70+2*6-80";
        // 2. 创建数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        // 3.定义索引进行扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";

        // 开始while循环的扫描expression
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (isOper(ch)) {
                // 判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    // 不为空
                    // 判断优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.getTop())) {
                        // 满足优先级
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 运算结果入数栈
                        numStack.push(res);
                        // 当前操作符入符号栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    // 为空,直接入栈
                    operStack.push(ch);
                }
            }
            else {
                // 字符的ASCII编码要考虑,
                // 不能发现一个数就立即入栈，因为他可能是多位数
                keepNum += (ch-48);
                // 如果ch是最后一位

                if(index==expression.length()-1){
                    numStack.push(Integer.valueOf(keepNum));
                }else {
                    // 查看后面的数是不是字符串
                    if (isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.valueOf(keepNum));
                        // 清空keepNum
                        keepNum = "";
                    }
                }




            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 从数栈和符号栈拿数据进行运算
        while (true) {
            //如果符号栈为空，则计算结束
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();

            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }


        System.out.printf("表达式%s = %d", expression, numStack.pop());
    }

    // 判断是不是运算符符
    public static boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
}

// 定义栈，需要扩展功能：
// 1.判断优先级，
// 2.判断是数还是符号
class ArrayStack2 {
    // 栈的大小
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满，无法放入数据");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空,没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 显示栈，从栈顶开始显示
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }

        int i = top;
        for (; i > -1; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    // 返回运算符的优先级,优先级由程序员来确定，使用数子表示
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    // 返回栈顶的值
    public int getTop() {
        return stack[top];
    }

}