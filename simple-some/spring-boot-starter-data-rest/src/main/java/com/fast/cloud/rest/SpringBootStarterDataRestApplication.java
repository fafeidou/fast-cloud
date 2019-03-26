package com.fast.cloud.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.fast.cloud.rest.repository")
@SpringBootApplication
public class SpringBootStarterDataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterDataRestApplication.class, args);
    }

}
