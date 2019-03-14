package com.fast.cloud.biz.kafka;

import com.fast.cloud.biz.kafka.component.KafkaSender;
import com.fast.cloud.biz.kafka.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonKafkaApplicationTests {

    @Autowired
    private KafkaSender<User> kafkaSender;

    @Test
    public void kafkaSend() throws InterruptedException {

        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setMsg(UUID.randomUUID().toString());
        user.setSendTime(new Date());
        kafkaSender.send(user);
//        //模拟发消息
//        for (int i = 0; i < 5; i++) {
//
//            User user = new User();
//            user.setId(System.currentTimeMillis());
//            user.setMsg(UUID.randomUUID().toString());
//            user.setSendTime(new Date());
//            kafkaSender.send(user);
//            Thread.sleep(3000);
//
//        }
    }

}

