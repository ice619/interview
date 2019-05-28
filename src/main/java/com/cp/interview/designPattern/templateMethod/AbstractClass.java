package com.cp.interview.designPattern.templateMethod;

public abstract class AbstractClass {
    //模板方法
    public void templateMethod(){
        primitiveOperation1();
        primitiveOperation2();
        primitiveOperation3();
    }

    public void primitiveOperation1(){
        if(primitiveOperation3()){
            System.out.println("执行 抽象类 primitiveOperation1");
        }else {
            System.out.println("不执行 抽象类 primitiveOperation1");
        }

    }

    public abstract void primitiveOperation2();

    //钩子方法
    public boolean primitiveOperation3(){
        System.out.println("抽象类 primitiveOperation3");
        return false;
    }
}
