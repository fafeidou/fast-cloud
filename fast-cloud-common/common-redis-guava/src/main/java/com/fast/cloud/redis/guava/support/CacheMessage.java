package com.fast.cloud.redis.guava.support;

import java.io.Serializable;

/**
 * @author qinfuxiang
 * @Date 2020/6/16 9:42
 */
public class CacheMessage implements Serializable {

    /** */
    private static final long serialVersionUID = 5987219310442078193L;

    private String cacheName;

    private Object key;

    public CacheMessage() {

    }
    public CacheMessage(String cacheName, Object key) {
        super();
        this.cacheName = cacheName;
        this.key = key;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

}
