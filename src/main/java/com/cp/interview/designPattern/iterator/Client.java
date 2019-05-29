package com.cp.interview.designPattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式
 */
public class Client {
    public static void main(String[] args) {
        List products = new ArrayList<>();
        products.add("苹果");
        products.add("犁");
        products.add("荔枝");

        AbstractObjectList productList = new ProductList(products);
        Iterator iterator = productList.createIterator();

        while(!iterator.isLast()){
            System.out.println(iterator.getNextItem() + ",");
            iterator.next();
        }

        while (!iterator.isFirst()){
            System.out.println(iterator.getPreviousItem() + ",");
            iterator.previous();
        }

    }
}
