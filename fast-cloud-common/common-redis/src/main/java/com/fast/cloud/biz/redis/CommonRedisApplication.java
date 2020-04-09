package com.fast.cloud.biz.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

@SpringBootApplication
public class CommonRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonRedisApplication.class, args);
    }

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "spring-cloud");
    }
}

