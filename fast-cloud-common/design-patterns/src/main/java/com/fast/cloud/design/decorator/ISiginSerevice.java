package com.fast.cloud.design.decorator;

/**
 * @Project: spring
 * @description: 抽象构件角色   登录的接口业务
 **/
public interface ISiginSerevice {
    String login(String username, String password);
}
