package com.fast.cloud.biz;

import com.fast.cloud.core.test.OutConfig;
import com.fast.cloud.core.test.TestConfig;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @author Batman.qin
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.fast.cloud")
public class FastCloudBizStarterApplication implements CommandLineRunner {
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(FastCloudBizStarterApplication.class, args);
    }

    @Autowired
    private OutConfig outConfig;
    @Override
    public void run(String... args) {
        Map<String, TestConfig> mapConfig = outConfig.getMapConfig();
    }
}

