package com.fast.cloud.core.pool;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池配置
 *
 * @author qinfuxiang
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "mms.executor.async")
public class ExecutorProperties {
    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 最大线程数
     */
    private int maxPoolSize;

    /**
     * 队列大小
     */
    private int queueCapacity;

    /**
     * 保持存活时间（秒）
     */
    private int keepAliveSeconds;

    /**
     * 线程名前缀
     */
    private String threadNamePrefix;
}
