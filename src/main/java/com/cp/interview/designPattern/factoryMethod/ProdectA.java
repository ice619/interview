package com.cp.interview.designPattern.factoryMethod;

/**
 * Description:
 *
 * @author chenpeng
 * @date 2019/5/24 16:10
 */
public class ProdectA implements Product {
    public ProdectA() {
        System.out.println("创建 ProdectA");
    }

    @Override
    public void methodSame() {
        System.out.println("productA 方法。。。");
    }
}
