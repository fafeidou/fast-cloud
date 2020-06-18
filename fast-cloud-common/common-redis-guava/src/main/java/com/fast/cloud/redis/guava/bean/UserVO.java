package com.fast.cloud.redis.guava.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qinfuxiang
 * @Date 2020/5/26 13:55
 */
public class UserVO implements Serializable {

    private String name;
    private String address;
    private Integer age;
    private String email = "";
    private Long id;

    private Date createTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
