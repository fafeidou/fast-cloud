package com.fast.cloud.mongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.fast.cloud.mongo.repository")
public class CommonMongoConfig {

}

