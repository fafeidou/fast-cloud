package com.fast.cloud.react;

import com.fast.cloud.core.swagger.CkSwaggerAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = CkSwaggerAutoConfiguration.class)
@ComponentScan(basePackages = "com.fast.cloud")
@EnableMongoRepositories(basePackages = "com.fast.cloud.react.repository")
public class FastCloudBizReactStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastCloudBizReactStarterApplication.class, args);
    }

}

