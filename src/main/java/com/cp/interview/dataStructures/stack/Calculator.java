package com.cp.interview.dataStructures.stack;

/**
 * 栈结构实现计算器
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "30+2*6-2";
        //创建俩个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数
        //循环扫描expression
        while(true){
            ch = expression.substring(index,index+1).charAt(0);
            if(operStack.isOper(ch)){
                //判断符号栈是否为空
                if(!operStack.isEmpty()){
                    //如果当前操作符优先级 小于等于栈中符号，则pop出数进行运算
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结果存入数栈
                        numStack.push(res);
                        //把当前操作符入符号栈
                        operStack.push(ch);
                    }else {
                        //如果当前操作符优先级大于栈中操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else {
                    //如果为空，直接入符号栈
                    operStack.push(ch);
                }
            }else {
                //如果是数字，则直接入数栈
                //numStack.push(ch - 48);  //这里记得减掉48，因为直接存ch存的是ASCII
                //如果是多为数字，不能立即入数栈，会出错
                //判断后一位，如果是符号才入栈
                keepNum += ch;

                //如果ch是表达式最后一位，直接入栈
                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断后一位如果是数字，就继续扫描，如果是符号，就入栈
                    if(operStack.isOper(expression.substring(index+1, index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index ++;
            if(index >= expression.length()){
                break;
            }
        }

        //表达式扫描完毕，就顺序从数栈符号栈弹出数据计算
        while(true){
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //把运算结果存入数栈
            numStack.push(res);
        }
        System.out.printf("表达式%s = %d",expression, numStack.pop());


    }
}

class ArrayStack2 {
    private int maxSize;//栈大小
    private int[] stack;//存数据
    private int top = -1;//top表示栈顶

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //返回栈顶的值，但是不弹出
    public int peek(){
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈（遍历）便利时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        //从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }

    //返回运算符优先级，
    //数字越大，则优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
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
}
