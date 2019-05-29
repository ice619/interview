package com.cp.interview.designPattern.iterator;

import java.util.List;

public class ProductIterator implements Iterator {

    private ProductList productList;
    private List products;
    /**
     * 定义一个游标，用于记录正向遍历的位置
     */
    private int cursor1;

    /**
     * 定义一个游标，用于记录逆向遍历的位置
     */
    private int cursor2;

    public ProductIterator(ProductList productList) {
        this.productList = productList;
        this.products = productList.getObjectList();
        this.cursor1 = 0;
        this.cursor2 = products.size() - 1;
    }

    @Override
    public void next() {
        if(cursor1 < products.size()){
            this.cursor1 ++;
        }
    }

    @Override
    public boolean isLast() {
        return (cursor1 == products.size());
    }

    @Override
    public void previous() {
        if(cursor2 > -1){
            this.cursor2 --;
        }
    }

    @Override
    public boolean isFirst() {
        return (cursor2 == -1);
    }

    @Override
    public Object getNextItem() {
        return products.get(cursor1);
    }

    @Override
    public Object getPreviousItem() {
        return products.get(cursor2);
    }
}
