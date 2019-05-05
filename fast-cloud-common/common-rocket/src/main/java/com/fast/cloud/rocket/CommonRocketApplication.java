package com.fast.cloud.rocket;

import com.fast.cloud.rocket.domain.OrderPaidEvent;
import com.qianmi.ms.starter.rocketmq.annotation.RocketMQMessageListener;
import com.qianmi.ms.starter.rocketmq.core.RocketMQListener;
import com.qianmi.ms.starter.rocketmq.core.RocketMQTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
public class CommonRocketApplication implements CommandLineRunner {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(CommonRocketApplication.class, args);
    }

    public void run(String... args) {
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));
//        rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate
    }

    @Service
    @RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
    public class MyConsumer1 implements RocketMQListener<String> {
        public void onMessage(String message) {
            log.info("received message: {}", message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
    public class MyConsumer2 implements RocketMQListener<OrderPaidEvent> {
        public void onMessage(OrderPaidEvent orderPaidEvent) {
            log.info("received orderPaidEvent: {}", orderPaidEvent);
        }
    }
}
