package com.fast.cloud.biz.rabbit.sender;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-22 14:44
 */
@Component
public class HelloSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String exchange, String routingKey) {
        String context = "你好现在是 " + new Date();
        System.out.println("send content = " + context);
        this.rabbitTemplate.setMandatory(true);
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.convertAndSend(exchange, routingKey, context);
    }

    /**
     * 确认后回调:
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            System.out.println("send ack fail, cause = " + cause);
        } else {
            System.out.println("send ack success");
        }
    }

    /**
     * 失败后return回调：
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("send fail return-message = " + new String(message.getBody()) + ", replyCode: " + replyCode + ", replyText: " + replyText + ", exchange: " + exchange + ", routingKey: " + routingKey);
    }
}