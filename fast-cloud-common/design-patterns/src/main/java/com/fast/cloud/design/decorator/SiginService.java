package com.fast.cloud.design.decorator;

/**
 * 具体构件角色    登录的具体实现
 * @author Batman.qin
 * @create 2019-05-07 9:34
 */
public class SiginService implements ISiginSerevice {
    /**
     * 登录的方法
     * @param username
     * @param password
     * @return
     */
    public String login(String username,String password){
        return "登陆成功";
    }
}
