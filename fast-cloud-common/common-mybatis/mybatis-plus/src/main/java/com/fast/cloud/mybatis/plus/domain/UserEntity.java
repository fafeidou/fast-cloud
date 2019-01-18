package com.fast.cloud.mybatis.plus.domain;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-17 15:09
 */

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@TableName("test_user")
@Data
public class UserEntity extends Model<UserEntity> implements Serializable {

    @TableId(value = "id")
    private String id;
    private String userName;
    private String password;
    //省略set get方法

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + userName + '\'' +
                ", createTime=" + password +
                '}';
    }
}