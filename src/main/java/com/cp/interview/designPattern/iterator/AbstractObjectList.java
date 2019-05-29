package com.cp.interview.designPattern.iterator;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractObjectList {
    private List<Object> objectList = new ArrayList<>();

    public AbstractObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    public void addObject(Object object){
        this.objectList.add(object);
    }

    public void removeObject(Object object){
        this.objectList.remove(object);
    }

    /**
     * 声明创建迭代器对象的抽象工厂方法
     * @return
     */
    public abstract Iterator createIterator();

}
