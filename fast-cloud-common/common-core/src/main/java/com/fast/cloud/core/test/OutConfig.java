package com.fast.cloud.core.test;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @Create 2018-11-30 14:42
 */
@Component
@ConfigurationProperties("ck.test")
@Data
public class OutConfig {
    private String title;
    private String description;
    private String version;

    private Map<String, TestConfig> mapConfig = new TreeMap<>(
            String.CASE_INSENSITIVE_ORDER);
}
