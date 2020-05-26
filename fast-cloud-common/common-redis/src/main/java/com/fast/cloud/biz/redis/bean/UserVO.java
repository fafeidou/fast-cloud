package com.fast.cloud.biz.redis.bean;

import java.io.Serializable;

/**
 * @author qinfuxiang
 * @Date 2020/5/26 13:55
 */
public class UserVO implements Serializable {

    private String name;
    private String address;
    private Integer age;
    private String email = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
