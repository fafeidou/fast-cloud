package com.fast.cloud.design.strategy;

import static com.fast.cloud.design.strategy.Constants.A;
import static com.fast.cloud.design.strategy.Constants.B;
import static com.fast.cloud.design.strategy.Constants.C;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-16 17:10
 */
public class StrategyMain {
    public static void main(String[] args) {
        Context context;

        context = new Context(A);
        context.contextInterface();

        context = new Context(B);
        context.contextInterface();

        context = new Context(C);
        context.contextInterface();
    }
}
