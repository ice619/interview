package com.cp.interview.designPattern.adapter;

public class Adapter extends Target {
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request(){
        adaptee.specificRequest();
    }
}
