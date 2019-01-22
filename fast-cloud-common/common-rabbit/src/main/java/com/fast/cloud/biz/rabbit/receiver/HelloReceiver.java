package com.fast.cloud.biz.rabbit.receiver;

import com.fast.cloud.biz.rabbit.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-22 14:45
 */
@Component
@RabbitListener(queues = RabbitConfig.queueName)
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello, Channel channel, Message message) throws IOException {
        try {
            Thread.sleep(2000);
            System.out.println("睡眠2s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉；否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("receiver success = " + hello);
        } catch (Exception e) {
            e.printStackTrace();
            //丢弃这条消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            System.out.println("receiver fail");
        }

    }

}