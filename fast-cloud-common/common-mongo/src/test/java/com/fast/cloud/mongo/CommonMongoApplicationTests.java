package com.fast.cloud.mongo;

import com.fast.cloud.mongo.domain.entity.Ad;
import com.fast.cloud.mongo.service.AdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonMongoApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(CommonMongoApplicationTests.class);
    @Autowired
    AdService adService;

    @Test
    public void contextLoads() {
        List<Ad> all = adService.findAll();
        System.out.println(all.size());

        Ad one = adService.findOne("2");
        System.out.println(one.getContent());

        logger.info("count{}===", adService.count());

        logger.info("exists{}===", adService.exists("2"));
    }
}

