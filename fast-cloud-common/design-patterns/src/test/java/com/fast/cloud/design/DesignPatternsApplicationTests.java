package com.fast.cloud.design;

import com.fast.cloud.design.spring.strategy.ContextStrategyFactory;
import com.fast.cloud.design.spring.strategy.IContextStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesignPatternsApplicationTests {

    @Resource
    private ContextStrategyFactory strategyFactory;

    @Test
    public void testStrategy(){
        IContextStrategy doStrategy = strategyFactory.doStrategy("aLIContextStrategy");
        doStrategy.say("张三");
    }

}

