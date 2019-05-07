package com.fast.cloud.design.decorator;

/**
 * 具体的装饰角色    原有的登录功能增加了发送短信的功能
 *
 * @author Batman.qin
 * @create 2019-05-07 9:39
 */
public class SiginForThirdService implements ISiginForThirdService {
    private ISiginSerevice siginSerevice;

    public SiginForThirdService(ISiginSerevice siginSerevice) {
        this.siginSerevice = siginSerevice;
    }

    @Override
    public String login(String username, String password) {
        String login = siginSerevice.login(username, password);
        //注册成功发送短信的功能
        System.out.println(login);
        String msg = sendShortMessage(username);
        return msg;
    }

    /**
     * 发送短信的功能   这个是装饰器 增加的额外的功能，在登录成功之后发送短信通知
     *
     * @param username
     * @return
     */
    @Override
    public String sendShortMessage(String username) {
        System.out.println("恭喜用户：" + username + "发送短信成功");
        return "发送短信成功";
    }
}
