package com.fast.cloud.design.spring.strategy;

import org.springframework.stereotype.Component;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-17 10:40
 */
@Component("bdUContextStrategy")
public class BDUContextStrategy implements IContextStrategy {

    @Override
    public void say(String name) {
        // TODO Auto-generated method stub
        System.out.println("百度集团欢迎你：" + name);
    }
}