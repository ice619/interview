package com.cp.interview.designPattern.proxy.dynamicproxy;

/**
 * Created by Administrator on 2018/6/12.
 */
public class MaotaiJiu implements SellWine {
    @Override
    public void sellWine() {
        System.out.println("我卖得是茅台酒。");
    }
}
