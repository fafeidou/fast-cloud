package com.fast.cloud.core.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author batman.qin
 */
@Configuration
@EnableConfigurationProperties(value = OutConfig.class)
@EnableAutoConfiguration
public class TestAutoConfiguration {
    private Logger logger = LoggerFactory.getLogger(TestAutoConfiguration.class);
    private final OutConfig outConfig;

    public TestAutoConfiguration(OutConfig outConfig) {
        this.outConfig = outConfig;
    }


}
