package com.fast.cloud.biz.rabbit.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.fast.cloud.biz.rabbit.config.TopicRabbitConfig.TOPIC_ONE;

/**
 * @Author Batman.qin
 * @Date 2019/5/24 10:31
 */
@Component("testHelloReceiver")
@RabbitListener(queues = TOPIC_ONE)
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

}
