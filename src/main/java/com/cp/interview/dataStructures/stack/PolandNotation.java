package com.cp.interview.dataStructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 */
public class PolandNotation {
    public static void main(String[] args) {

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式："+infixExpressionList);

        List<String> parseSuffixExpression = parseSuffixExpression(infixExpressionList);
        System.out.println("后缀表达式：" + parseSuffixExpression);

        //逆波兰（后缀）表达式，
//        String suffixExpression = "3 4 + 5 * 6 -";
//        List<String> rpnList = getListString(suffixExpression);

        int res = calculate(parseSuffixExpression);
        System.out.println("计算结果是：" + res);

    }
    //将中缀表达式转成后缀表达式
    public static List<String> parseSuffixExpression(List<String> ls){
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        for(String item : ls){
            if(item.matches("\\d+")){
                s2.add(item);//如果是数字，直接加入s2
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号，则依次弹出S1运算符，
                // 压入到s2，直到遇到左括号为止，此时将这对括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将左括号弹出，消除小括号
            }else {
                //当item优先级<= s1栈顶优先级，将s1栈顶弹出并压入到s2，
                while(s1.size() != 0 && Operation.getValue(item) <= Operation.getValue(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中剩余运算符依次弹出并压入s2
        while(s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //将中缀表达式转换成对应的List
    public static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        do{
            //如果是非数字，就加入ls
            if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                ls.add("" + c);
                i ++;
            }else {
                //如果是数字，要考虑多位数
                str = "";
                while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;
    }

    //将表达式放到一个list中
    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for(String ele: split){
            list.add(ele);
        }
        return list;
    }

    //完成计算
    public static  int calculate(List<String> ls){
        Stack<String> stack = new Stack<>();
        for(String item: ls){
            if(item.matches("\\d+")){//匹配多位数
                //如果是数 直接入栈
                stack.push(item);
            }else {
                //如果碰上符号,pop出俩个数，并运算再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //结果入栈
                stack.push(res + "");

            }
        }
        //最后留在stack中的数就是运算结果
        return Integer.parseInt(stack.pop());

    }

}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回优先级
    public static int getValue(String operation){
        int result = 0;
        switch(operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
