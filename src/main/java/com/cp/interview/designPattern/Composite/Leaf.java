package com.cp.interview.designPattern.Composite;

public class Leaf implements Component {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void add(Component c) {

    }

    @Override
    public void remove(Component c) {

    }

    @Override
    public Component getChild(int i) {
        return null;
    }

    @Override
    public void operation(int depth) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < depth; i ++){
            str.append("*");
        }
        System.out.println(str + name);
    }
}
