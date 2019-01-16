package com.fast.cloud.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Batman.qin
 */
@SpringBootApplication(scanBasePackages = {"com.fast.cloud"})
public class FastCloudBizStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastCloudBizStarterApplication.class, args);
    }
}

