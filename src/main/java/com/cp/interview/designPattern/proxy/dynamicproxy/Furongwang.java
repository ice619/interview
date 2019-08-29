package com.cp.interview.designPattern.proxy.dynamicproxy;

/**
 * Created by Administrator on 2018/6/12.
 */
public class Furongwang implements SellCigarette {
    @Override
    public void sellCigarette() {
        System.out.println("售卖的是正宗的芙蓉王，可以扫描条形码查证。");
    }
}
