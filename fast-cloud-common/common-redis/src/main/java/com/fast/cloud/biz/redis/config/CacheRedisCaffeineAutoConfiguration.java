package com.fast.cloud.biz.redis.config;

import com.fast.cloud.biz.redis.bean.CacheRedisCaffeineProperties;
import com.fast.cloud.biz.redis.support.CacheMessageListener;
import com.fast.cloud.biz.redis.support.RedisCaffeineCacheManager;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author qinfuxiang
 * @Date 2020/6/16 9:41
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(CacheRedisCaffeineProperties.class)
public class CacheRedisCaffeineAutoConfiguration {

    @Autowired
    private CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

    @Bean(name = "cacheManager")
    public RedisCaffeineCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
        return new RedisCaffeineCacheManager(cacheRedisCaffeineProperties, redisTemplate);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory singleConnectionFactory,
        RedisSerializer redisSerializer) {
        return create(singleConnectionFactory, redisSerializer);
    }

    @Bean
    public RedisSerializer<Object> redisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        //反序列化时候遇到不匹配的属性并不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //序列化时候遇到空对象不抛出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //反序列化的时候如果是无效子类型,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        //不使用默认的dateTime进行序列化,
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        //使用JSR310提供的序列化类,里面包含了大量的JDK8时间序列化类
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    private RedisTemplate<Object, Object> create(RedisConnectionFactory connectionFactory,
        RedisSerializer redisSerializer) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        RedisSerializer<?> stringRedisSerializer = RedisSerializer.string();
        // key-value结构序列化数据结构
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        // hash数据结构序列化方式,必须这样否则存hash 就是基于jdk序列化的
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        // 启用默认序列化方式
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.setDefaultSerializer(redisSerializer);
        return redisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
        RedisTemplate<Object, Object> redisTemplate,
        RedisCaffeineCacheManager redisCaffeineCacheManager) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisTemplate.getConnectionFactory());
        CacheMessageListener cacheMessageListener = new CacheMessageListener(redisTemplate,
            redisCaffeineCacheManager);
        redisMessageListenerContainer.addMessageListener(cacheMessageListener,
            new ChannelTopic(cacheRedisCaffeineProperties.getRedis().getTopic()));
        return redisMessageListenerContainer;
    }
}
