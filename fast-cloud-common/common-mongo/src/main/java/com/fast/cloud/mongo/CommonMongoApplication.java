package com.fast.cloud.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.fast.cloud.mongo.repository")
@ComponentScan(basePackages = "com.fast.cloud")
public class CommonMongoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonMongoApplication.class, args);
    }

}

