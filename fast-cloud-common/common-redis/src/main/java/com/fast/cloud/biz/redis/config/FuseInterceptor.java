package com.fast.cloud.biz.redis.config;

/**
 * @author qinfuxiang
 * @Date 2020/6/15 10:11
 */
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.micrometer.core.instrument.util.StringUtils;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class FuseInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置超时时间。guava的Cache
     */
    public static Cache<String, Object> moduleCache = CacheBuilder.
        newBuilder().
        //写入缓存后，10s后过期
            expireAfterWrite(10, TimeUnit.SECONDS).
            build();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ExecutionException {
        Object ifPresent = moduleCache.getIfPresent("");
        //先在本地缓存中获取内容，若获取不到，直接查询Redis并放入本地缓存（线程安全）
        String moduleStatus = (String) moduleCache.get("topic:service_xx:module", () -> {
            //若本地缓存获取不到，那么去Redis查询数据并放入缓存。
            String value = stringRedisTemplate.opsForValue().get("service_xx:module");
            return StringUtils.isBlank(value) ? "close": value;
        });
        //拦截器处理。
        return true;
    }

    /**
     * 订阅发布获取到最新的Redis内容
     *
     * @param type 监听队列的名
     * @param message 监听到的信息
     */
    public void refreshCache(String type, String message) {
        if ("topic:service_xx".equals(type)) {
            //若是这个队列的消息，那么将其转换为Set<String>结构，并放入缓存中
            Set<String> patterns = JSONObject.parseObject(message,
                new TypeReference<LinkedHashSet<String>>() {
                }.getType());
            moduleCache.put(type, patterns);
        } else {
            moduleCache.put(type, message);
        }
    }
}