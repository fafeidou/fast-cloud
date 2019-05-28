package com.fast.cloud.sidecarclient.entity;

import java.io.Serializable;

/**
 * @Author Batman.qin
 * @Date 2019/5/28 11:42
 */
public class Student implements Serializable {
    private String name;
    private Integer score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
