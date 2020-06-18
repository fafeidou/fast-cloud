package com.fast.cloud.redis.guava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CommonRedisGuavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonRedisGuavaApplication.class, args);
    }

}
