package com.cp.interview.designPattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2018/6/12.
 * jdk动态代理
 */
public class Test {
    public static void main(String[] args) {
        MaotaiJiu maotaiJiu = new MaotaiJiu();
        Wuliangye wuliangye = new Wuliangye();
        Furongwang furongwang = new Furongwang();

        InvocationHandler jingxiao1 = new GuitaiA(maotaiJiu);
        SellWine dynamicProxy1 = (SellWine) Proxy.newProxyInstance(MaotaiJiu.class.getClassLoader(), MaotaiJiu.class.getInterfaces(), jingxiao1);
        dynamicProxy1.sellWine();

        InvocationHandler jingxiao2 = new GuitaiA(wuliangye);
        SellWine dynamicProxy2 = (SellWine) Proxy.newProxyInstance(Wuliangye.class.getClassLoader(), Wuliangye.class.getInterfaces(), jingxiao2);
        dynamicProxy2.sellWine();

        InvocationHandler jingxiao3 = new GuitaiA(furongwang);
        SellCigarette dynamicProxy3 = (SellCigarette) Proxy.newProxyInstance(Furongwang.class.getClassLoader(), Furongwang.class.getInterfaces(), jingxiao3);
        dynamicProxy3.sellCigarette();

        System.out.println("dynamicProxy class name:"+dynamicProxy1.getClass().getName());
        System.out.println("dynamicProxy1 class name:"+dynamicProxy2.getClass().getName());
        System.out.println("dynamicProxy3 class name:"+dynamicProxy3.getClass().getName());

    }
}
