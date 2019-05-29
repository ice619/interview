package com.cp.interview.designPattern.Composite;

/**
 * 组合模式
 */
public class Client {
    public static void main(String[] args) {
        Compsite root = new Compsite("root");
        root.add(new Leaf("leaf 1_A"));
        root.add(new Leaf("leaf 1_B"));

        Compsite compsite = new Compsite("compsite 1_A");
        compsite.add(new Leaf("leaf 2_A"));

        root.add(compsite);

        Compsite compsite2 = new Compsite("compsite 2_A");
        compsite2.add(new Leaf("leaf 3_B"));

        compsite.add(compsite2);

        root.add(new Leaf("leaf C"));

        root.operation(1);


    }
}
