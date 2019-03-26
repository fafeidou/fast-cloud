package com.fast.cloud.biz.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonRedisApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        RedisOperations operations = redisTemplate.opsForHash().getOperations();
        operations.opsForHash().put("test", "aaaaaa6", "sdfsdfsdfdsf");
        operations.expire("aaaaaa6", 2, TimeUnit.SECONDS);
    }

}

