package com.fast.cloud.design.strategy;

import static com.fast.cloud.design.strategy.Constants.A;
import static com.fast.cloud.design.strategy.Constants.B;
import static com.fast.cloud.design.strategy.Constants.C;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-16 17:09
 */
public class Context {
    Strategy strategy;

    public Context(String type) {
        switch (type){
            case A:
                strategy = new ConcreteStrategyA();
                break;
            case B:
                strategy = new ConcreteStrategyB();
                break;
            case C:
                strategy = new ConcreteStrategyC();
                break;
        }
    }

    public void contextInterface() {
        strategy.AlgorithmInterface();
    }
}
