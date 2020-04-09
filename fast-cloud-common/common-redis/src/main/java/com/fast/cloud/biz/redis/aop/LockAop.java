package com.fast.cloud.biz.redis.aop;

/**
 * @Classname LogAop
 * @Description TODO
 * @Date 2020/4/8 11:48
 * @Created by qinfuxiang
 */

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Component
@Aspect
@Slf4j
public class LockAop {

    private WebApplicationContext webApplicationContext;

    public LockAop(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Pointcut("@annotation(com.fast.cloud.biz.redis.aop.LockDistributed)")
    private void apiAop() {

    }

    @Around("apiAop()")
    public Object aroundApi(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LockDistributed LockDistributed = method.getAnnotation(LockDistributed.class);
        String localRegistry = LockDistributed.value();
        if (StringUtils.isEmpty(localRegistry)) {
            throw new RuntimeException("获取 Registry beann 失败");
        }

        RedisLockRegistry redisLockRegistry = (RedisLockRegistry) webApplicationContext.getBean(LockDistributed.value());

        Lock lock = redisLockRegistry.obtain(signature.getName());
        boolean b = false;
        for (int i = 0; i < 3; i++) {
            b = lock.tryLock(LockDistributed.time(), TimeUnit.SECONDS);
            if (b) {
                break;
            } else {
                continue;
            }
        }
        log.info("获取锁=====" + b);
        Object proceed = null;
        try {
            proceed = point.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                lock.unlock();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }


        return proceed;

    }

}


