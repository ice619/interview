package com.cp.interview.designPattern.command;

public class ConcreteCommand extends Command {
    private Receiver receiver;

    public ConcreteCommand() {
        this.receiver = new Receiver();
    }

    @Override
    public void execute() {
        System.out.println("ConcreteCommand 调用 Receiver");
        receiver.action();
    }
}
