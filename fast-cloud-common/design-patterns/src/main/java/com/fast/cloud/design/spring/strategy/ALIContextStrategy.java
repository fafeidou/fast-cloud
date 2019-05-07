package com.fast.cloud.design.spring.strategy;

import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-17 10:39
 */
@Component("aLIContextStrategy")
public class ALIContextStrategy implements IContextStrategy {

    @Override
    public void say(String name) {
        System.out.println("阿里巴巴集团欢迎你：" + name);
    }

}
