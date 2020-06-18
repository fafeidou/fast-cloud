package com.fast.cloud.redis.guava.support;


import com.fast.cloud.redis.guava.config.CacheRedisGuavaProperties;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

/**
 * @author qinfuxiang
 * @Date 2020/6/16 9:43
 */
public class RedisGuavaCache extends AbstractValueAdaptingCache {

    private final Logger logger = LoggerFactory.getLogger(RedisGuavaCache.class);

    private String name;

    private RedisTemplate<Object, Object> stringKeyRedisTemplate;

    private com.google.common.cache.Cache<Object, Object> loadingCache;

    private String cachePrefix;

    private long defaultExpiration = 0;

    private Map<String, Long> expires;

    private String topic = "cache:redis:caffeine:topic";

    private Map<String, ReentrantLock> keyLockMap = new ConcurrentHashMap<String, ReentrantLock>();

    public RedisGuavaCache(boolean allowNullValues) {
        super(allowNullValues);
    }

    public RedisGuavaCache(String name, RedisTemplate<Object, Object> stringKeyRedisTemplate,
        com.google.common.cache.Cache<Object, Object> loadingCache,
        CacheRedisGuavaProperties cacheRedisCaffeineProperties) {
        super(cacheRedisCaffeineProperties.isCacheNullValues());
        this.name = name;
        this.stringKeyRedisTemplate = stringKeyRedisTemplate;
        this.loadingCache = loadingCache;
        this.cachePrefix = cacheRedisCaffeineProperties.getCachePrefix();
        this.defaultExpiration = cacheRedisCaffeineProperties.getRedis().getDefaultExpiration();
        this.expires = cacheRedisCaffeineProperties.getRedis().getExpires();
        this.topic = cacheRedisCaffeineProperties.getRedis().getTopic();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        Object value = lookup(key);
        if (value != null) {
            return (T) value;
        }

        ReentrantLock lock = keyLockMap.get(key.toString());
        if (lock == null) {
            logger.debug("create lock for key : {}", key);
            lock = new ReentrantLock();
            keyLockMap.putIfAbsent(key.toString(), lock);
        }
        try {
            lock.lock();
            value = lookup(key);
            if (value != null) {
                return (T) value;
            }
            value = valueLoader.call();
            Object storeValue = toStoreValue(value);
            put(key, storeValue);
            return (T) value;
        } catch (Exception e) {
            throw new ValueRetrievalException(key, valueLoader, e.getCause());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put(Object key, Object value) {
        if (!super.isAllowNullValues() && value == null) {
            this.evict(key);
            return;
        }
        long expire = getExpire();
        if (expire > 0) {
            stringKeyRedisTemplate.opsForValue().set(getKey(key), toStoreValue(value), expire, TimeUnit.MILLISECONDS);
        } else {
            stringKeyRedisTemplate.opsForValue().set(getKey(key), toStoreValue(value));
        }

        push(new CacheMessage(this.name, key));

        loadingCache.put(key, value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        Object cacheKey = getKey(key);
        Object prevValue = null;
        // 考虑使用分布式锁，或者将redis的setIfAbsent改为原子性操作
        synchronized (key) {
            prevValue = stringKeyRedisTemplate.opsForValue().get(cacheKey);
            if (prevValue == null) {
                long expire = getExpire();
                if (expire > 0) {
                    stringKeyRedisTemplate.opsForValue()
                        .set(getKey(key), toStoreValue(value), expire, TimeUnit.MILLISECONDS);
                } else {
                    stringKeyRedisTemplate.opsForValue().set(getKey(key), toStoreValue(value));
                }

                push(new CacheMessage(this.name, key));

                loadingCache.put(key, toStoreValue(value));
            }
        }
        return toValueWrapper(prevValue);
    }

    @Override
    public void evict(Object key) {
        // 先清除redis中缓存数据，然后清除caffeine中的缓存，避免短时间内如果先清除caffeine缓存后其他请求会再从redis里加载到caffeine中
        stringKeyRedisTemplate.delete(getKey(key));

        push(new CacheMessage(this.name, key));

        loadingCache.invalidate(key);
    }

    @Override
    public void clear() {
        // 先清除redis中缓存数据，然后清除caffeine中的缓存，避免短时间内如果先清除caffeine缓存后其他请求会再从redis里加载到caffeine中
        Set<Object> keys = stringKeyRedisTemplate.keys(this.name.concat(":*"));
        for (Object key : keys) {
            stringKeyRedisTemplate.delete(key);
        }

        push(new CacheMessage(this.name, null));

        loadingCache.invalidateAll();
    }

    @Override
    protected Object lookup(Object key) {
        Object cacheKey = getKey(key);
        Object value = loadingCache.getIfPresent(key);
        if (value != null) {
            logger.debug("get cache from guava, the key is : {}", cacheKey);
            return value;
        }

        value = stringKeyRedisTemplate.opsForValue().get(cacheKey);

        if (value != null) {
            logger.debug("get cache from redis and put in guava, the key is : {}", cacheKey);
            loadingCache.put(key, value);
        }
        return value;
    }

    private Object getKey(Object key) {
        return this.name.concat(":")
            .concat(StringUtils.isEmpty(cachePrefix) ? key.toString() : cachePrefix.concat(":").concat(key.toString()));
    }

    private long getExpire() {
        long expire = defaultExpiration;
        Long cacheNameExpire = expires.get(this.name);
        return cacheNameExpire == null ? expire : cacheNameExpire.longValue();
    }

    /**
     * @description 缓存变更时通知其他节点清理本地缓存
     */
    private void push(CacheMessage message) {
        stringKeyRedisTemplate.convertAndSend(topic, message);
    }

    /**
     * @description 清理本地缓存
     */
    public void clearLocal(Object key) {
        logger.debug("clear local cache, the key is : {}", key);
        if (key == null) {
            loadingCache.invalidateAll();
        } else {
            loadingCache.invalidate(key);
        }
    }
}

