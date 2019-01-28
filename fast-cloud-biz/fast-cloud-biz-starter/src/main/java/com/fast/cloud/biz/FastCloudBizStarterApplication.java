package com.fast.cloud.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Batman.qin
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.fast.cloud")
public class FastCloudBizStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastCloudBizStarterApplication.class, args);
    }
}

