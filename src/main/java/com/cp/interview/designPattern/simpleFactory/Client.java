package com.cp.interview.designPattern.simpleFactory;

/**
 * Description: 简单工厂
 *
 * @author chenpeng
 * @date 2019/5/24 16:15
 */
public class Client {
    public static void main(String[] args) {
        Product product;
        product = SimpleFactory.getProduct("productA");
        product.methodSame();
    }
}
