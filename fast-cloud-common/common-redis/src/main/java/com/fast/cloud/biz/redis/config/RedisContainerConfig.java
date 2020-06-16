package com.fast.cloud.biz.redis.config;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author qinfuxiang
 * @Date 2020/6/15 10:09
 */
@Slf4j
@Configuration
public class RedisContainerConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
        MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //设置监听的队列，可以设置多个
        container.addMessageListener(listenerAdapter, new PatternTopic("topic:service_xx:module"));
        container.addMessageListener(listenerAdapter, new PatternTopic("topic:service_xx"));
        return container;
    }

    //FuseInterceptor是一个普通的Bean对象，他的作用是处理监听到的内容
    @Bean
    MessageListenerAdapter listenerAdapter(FuseInterceptor receiver) {
        return new MessageListenerAdapter((MessageListener) (message, pattern) -> {
            try {
                //监听的队列信息
                String type = new String(message.getChannel(), StandardCharsets.UTF_8.name());
                //发送的内容信息
                String m = new String(message.getBody(), StandardCharsets.UTF_8.name());
                log.info("redis监听到[{}]的消息[{}]", type, m);
                receiver.refreshCache(type, m);
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
            }
        });
    }
}
