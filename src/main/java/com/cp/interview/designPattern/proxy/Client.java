package com.cp.interview.designPattern.proxy;

/**
 * 代理模式
 */
public class Client {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}
