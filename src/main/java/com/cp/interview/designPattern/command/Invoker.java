package com.cp.interview.designPattern.command;

public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void call(){
        System.out.println("Invoker 调用 Command");
        command.execute();
    }
}
