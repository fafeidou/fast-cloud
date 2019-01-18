package com.fast.cloud.design.spring.strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-17 10:41
 */
@Data
@Component
public class ContextStrategyFactory {
    @Autowired
    private Map<String, IContextStrategy> contextStrategy = new ConcurrentHashMap<>();

    public IContextStrategy doStrategy(String type) {
        return this.contextStrategy.get(type);
    }
}
