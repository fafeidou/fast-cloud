package com.fast.cloud.redis.guava.support;


import com.fast.cloud.redis.guava.config.CacheRedisGuavaProperties;
import com.google.common.cache.CacheBuilder;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author qinfuxiang
 * @Date 2020/6/16 9:44
 */
public class RedisGuavaCacheManager implements CacheManager {

    private final Logger logger = LoggerFactory.getLogger(RedisGuavaCacheManager.class);

    private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>();

    private CacheRedisGuavaProperties cacheRedisGuavaProperties;

    private RedisTemplate<Object, Object> stringKeyRedisTemplate;

    private boolean dynamic = true;

    private Set<String> cacheNames;

    public RedisGuavaCacheManager(CacheRedisGuavaProperties cacheRedisGuavaProperties,
        RedisTemplate<Object, Object> stringKeyRedisTemplate) {
        super();
        this.cacheRedisGuavaProperties = cacheRedisGuavaProperties;
        this.stringKeyRedisTemplate = stringKeyRedisTemplate;
        this.dynamic = cacheRedisGuavaProperties.isDynamic();
        this.cacheNames = cacheRedisGuavaProperties.getCacheNames();
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = cacheMap.get(name);
        if (cache != null) {
            return cache;
        }
        if (!dynamic && !cacheNames.contains(name)) {
            return cache;
        }

        cache = new RedisGuavaCache(name, stringKeyRedisTemplate, guavaCache(), cacheRedisGuavaProperties);
        Cache oldCache = cacheMap.putIfAbsent(name, cache);
        logger.debug("create cache instance, the cache name is : {}", name);
        return oldCache == null ? cache : oldCache;
    }

    public com.google.common.cache.Cache<Object, Object> guavaCache() {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder();

        if (cacheRedisGuavaProperties.getGuava().getExpireAfterAccess() > 0) {
            cacheBuilder.expireAfterAccess(cacheRedisGuavaProperties.getGuava().getExpireAfterAccess(),
                TimeUnit.MILLISECONDS);
        }
        if (cacheRedisGuavaProperties.getGuava().getExpireAfterWrite() > 0) {
            cacheBuilder.expireAfterWrite(cacheRedisGuavaProperties.getGuava().getExpireAfterWrite(),
                TimeUnit.MILLISECONDS);
        }
        if (cacheRedisGuavaProperties.getGuava().getInitialCapacity() > 0) {
            cacheBuilder.initialCapacity(cacheRedisGuavaProperties.getGuava().getInitialCapacity());
        }
        if (cacheRedisGuavaProperties.getGuava().getMaximumSize() > 0) {
            cacheBuilder.maximumSize(cacheRedisGuavaProperties.getGuava().getMaximumSize());
        }
        if (cacheRedisGuavaProperties.getGuava().getRefreshAfterWrite() > 0) {
            cacheBuilder.refreshAfterWrite(cacheRedisGuavaProperties.getGuava().getRefreshAfterWrite(),
                TimeUnit.MILLISECONDS);
        }
        return cacheBuilder.build();
    }

    @Override
    public Collection<String> getCacheNames() {
        return this.cacheNames;
    }

    public void clearLocal(String cacheName, Object key) {
        Cache cache = cacheMap.get(cacheName);
        if (cache == null) {
            return;
        }

        RedisGuavaCache redisGuavaCache = (RedisGuavaCache) cache;
        redisGuavaCache.clearLocal(key);
    }
}

