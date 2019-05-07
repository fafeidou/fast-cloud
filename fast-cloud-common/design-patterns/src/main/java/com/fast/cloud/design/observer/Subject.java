package com.fast.cloud.design.observer;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-17 14:43
 */
public interface Subject {
    /**
     * 增加订阅者
     *
     * @param observer
     */
    void attach(Observer observer);

    /**
     * 删除订阅者
     *
     * @param observer
     */
    void detach(Observer observer);

    /**
     * 通知订阅者更新消息
     */
    void notify(String message);
}
