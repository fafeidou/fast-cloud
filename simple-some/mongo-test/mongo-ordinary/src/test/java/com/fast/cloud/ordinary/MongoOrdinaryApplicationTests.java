package com.fast.cloud.ordinary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoOrdinaryApplicationTests {
    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    public void contextLoads() {
    }

}
