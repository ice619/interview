package com.cp.interview.designPattern.strategy;

/**
 * Description:
 *
 * @author chenpeng
 * @date 2019/5/24 16:42
 */
public class Context {
    private Strategy strategy;
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public void algorithm(){
        strategy.algorithm();
    }
}
