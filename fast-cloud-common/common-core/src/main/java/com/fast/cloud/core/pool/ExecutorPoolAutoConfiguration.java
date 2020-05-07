package com.fast.cloud.core.pool;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 配置异步任务线程池
 * @author qinfuxiang
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ExecutorProperties.class)
public class ExecutorPoolAutoConfiguration {

    @Bean
    @ConditionalOnBean(ExecutorProperties.class)
    public EcAsyncConfigurer asyncConfigurer(ExecutorProperties executorProperties) {
        return new EcAsyncConfigurer(executorProperties);
    }

    /**
     * 自定义实现异步线程池配置
     */
    @Slf4j
    @AllArgsConstructor
    public static class EcAsyncConfigurer implements AsyncConfigurer {

        private ExecutorProperties executorProperties;

        @Override
        public Executor getAsyncExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(executorProperties.getCorePoolSize());
            executor.setMaxPoolSize(executorProperties.getMaxPoolSize());
            executor.setWaitForTasksToCompleteOnShutdown(true);
            executor.setAwaitTerminationSeconds(executorProperties.getKeepAliveSeconds());
            executor.setThreadNamePrefix(executorProperties.getThreadNamePrefix());
            executor.setQueueCapacity(executorProperties.getQueueCapacity());
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            executor.initialize();
            return executor;
        }

        @Override
        public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
            return (e, m, params) -> {
                log.error("async execute exception: {}", e.getMessage(), e);
            };
        }

    }

}
