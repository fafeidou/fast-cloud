package com.fast.cloud.design.decorator;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-05-07 9:41
 */
public class SignTest {
    public static void main(String[] args) {

        ISiginSerevice siginSerevice = new SiginService();

        ISiginSerevice  siginForThirdService = new SiginForThirdService(siginSerevice);

        siginForThirdService.login("sunkang","4324");
    }
}
