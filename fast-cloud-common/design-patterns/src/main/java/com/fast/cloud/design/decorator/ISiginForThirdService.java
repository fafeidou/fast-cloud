package com.fast.cloud.design.decorator;

/**
 * 装饰角色    拓展了发送短信的功能
 *
 * @author Batman.qin
 * @create 2019-05-07 9:38
 */
public interface ISiginForThirdService extends ISiginSerevice {
    /**
     * 原有登录的方法
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    String login(String username, String password);

    /**
     * 发送短信
     *
     * @param msg
     * @return
     */
    String sendShortMessage(String msg);
}
