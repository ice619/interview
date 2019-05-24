package com.cp.interview.designPattern.simpleFactory;

/**
 * Description:
 *
 * @author chenpeng
 * @date 2019/5/24 16:11
 */
public class ProdectB implements Product {
    public ProdectB() {
        System.out.println("创建 ProdectB");
    }

    @Override
    public void methodSame() {
        System.out.println("prodectB 方法。。。");
    }
}
