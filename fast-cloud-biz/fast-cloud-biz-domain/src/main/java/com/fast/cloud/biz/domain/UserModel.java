package com.fast.cloud.biz.domain;

import lombok.Data;

import javax.persistence.Table;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-15 9:43
 */
@Data
@Table(name = "test_user")
public class UserModel {
    private String id;
    private String userName;
    private String password;
}
