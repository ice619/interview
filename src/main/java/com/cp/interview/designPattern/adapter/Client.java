package com.cp.interview.designPattern.adapter;

/**
 * 适配器模式
 */
public class Client {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }
}
