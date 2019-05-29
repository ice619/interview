package com.cp.interview.designPattern.state;

public abstract class State {
    protected Context context;
    public abstract void handle(Context context);
}
