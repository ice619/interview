package com.cp.interview.designPattern.Composite;

import java.util.ArrayList;
import java.util.List;

public class Compsite implements Component {
    private List<Component> componentList = new ArrayList<>();

    private String name;

    public Compsite(String name) {
        this.name = name;
    }

    @Override
    public void add(Component c) {
        componentList.add(c);
    }

    @Override
    public void remove(Component c) {
        componentList.remove(c);
    }

    @Override
    public Component getChild(int i) {
        return componentList.get(i);
    }

    @Override
    public void operation(int depth) {

        StringBuilder str = new StringBuilder();
        for(int i = 0; i < depth; i ++){
            str.append("*");
        }
        System.out.println(str + name);

        for(Component component : componentList){
            component.operation(depth + 2);
        }
    }
}
