package com.fast.cloud.mybatis.plus.domain;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-17 15:09
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@Data
public class UserEntity extends Model<UserEntity> implements Serializable {

    @TableId(value = "user_id")
    private String id;
    private String username;
    private String password;

    @Version
    private Integer version;
    //省略set get方法

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", createTime=" + password +
                '}';
    }
}