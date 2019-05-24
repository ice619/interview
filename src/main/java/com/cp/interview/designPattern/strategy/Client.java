package com.cp.interview.designPattern.strategy;

/**
 * Description:策略模式
 *
 * @author chenpeng
 * @date 2019/5/24 16:44
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        Strategy strategy;
        strategy = new StrategyA();
        context.setStrategy(strategy);
        context.algorithm();

        //策略 + 简单工厂
        ContextWithSimpleFactory contextWithSimpleFactory = new ContextWithSimpleFactory("b");
        contextWithSimpleFactory.algorithm();
    }
}
