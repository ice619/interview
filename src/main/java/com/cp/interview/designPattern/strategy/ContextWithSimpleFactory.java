package com.cp.interview.designPattern.strategy;

/**
 * Description:策略模式和简单工厂模式结合
 *
 * @author chenpeng
 * @date 2019/5/24 16:51
 */
public class ContextWithSimpleFactory {
    private Strategy strategy;

    public ContextWithSimpleFactory(String strategyStr) {
        switch (strategyStr){
            case "a":
                strategy = new StrategyA();
                break;
            case "b":
                strategy = new StrategyB();
                break;
        }
    }

    public void algorithm(){
        strategy.algorithm();
    }
}
