package com.cp.interview.designPattern.iterator;

import java.util.List;

public class ProductList extends AbstractObjectList {

    public ProductList(List<Object> objectList) {
        super(objectList);
    }

    @Override
    public Iterator createIterator() {
        return new ProductIterator(this);
    }
}
