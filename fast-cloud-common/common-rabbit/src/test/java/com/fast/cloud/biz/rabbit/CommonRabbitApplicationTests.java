package com.fast.cloud.biz.rabbit;

import com.fast.cloud.biz.rabbit.config.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonRabbitApplicationTests {
    @Autowired
    private TopicSender topicSender;

    @Test
    public void contextLoads() {
        topicSender.send_one();
    }

}

