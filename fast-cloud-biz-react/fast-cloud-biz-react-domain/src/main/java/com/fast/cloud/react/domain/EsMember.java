package com.fast.cloud.react.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yajun.dong on 2019/1/23 0023.
 */
@Table(name = "es_member")
@Data
@CompoundIndexes(
        {
                @CompoundIndex(def = "{'username':1}", unique = true),
//                @CompoundIndex(def = "{'nickname':1,'mobile':1}", unique = true)
        }
)
@Document(collection = "es_member")
public class EsMember {

    private Long id;

    private Date createDate;

    private Date modifyDate;

    private Integer type;

    private String username;

    private String password;

    /**
     * E-mail
     */
    private String email;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birth;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 电话
     */
    private String phone;

    /**
     * 身份证号
     */
    private String identityCard;

    /**
     * 会员注册项0
     */
    private String attributeValue0;

    /**
     * 会员注册项1
     */
    private String attributeValue1;

    /**
     * 会员注册项2
     */
    private String attributeValue2;

    /**
     * 会员注册项3
     */
    private String attributeValue3;

    /**
     * 会员注册项4
     */
    private String attributeValue4;

    /**
     * 会员注册项5
     */
    private String attributeValue5;

    /**
     * 会员注册项6
     */
    private String attributeValue6;

    /**
     * 会员注册项7
     */
    private String attributeValue7;

    /**
     * 会员注册项8
     */
    private String attributeValue8;

    /**
     * 会员注册项9
     */
    private String attributeValue9;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 消费金额
     */
    private BigDecimal amount;

    /**
     * 积分
     */
    private Long point;

    /**
     * 是否启用
     */
    private Boolean isEnabled;

    /**
     * 是否锁定
     */
    private Boolean isLocked;

    /**
     * 连续登录失败次数
     */
    private Integer loginFailureCount;

    /**
     * 锁定日期
     */
    private Date lockedDate;

    /**
     * 注册IP
     */
    private String registerIp;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录日期
     */
    private Date loginDate;

    /**
     * 锁定KEY
     */
    private String lockKey;

    /**
     * 会员等级
     */
    private String memberRank;

    private String area;

    /**
     * 设备号
     */
    private String deviceId;

    /**
     * 设备类型
     */
    private String appType;

    /**
     * app所在版本
     */
    private String appVersion;

    private Date appLoginDate;

    /*vip卡号*/
    private String vipNo;

    /**
     * 状态
     */
    private String accountState;

    /**
     * 尺码
     */
    private String footSize;

    /** 搜索历史 */
//    private String searchHistory;

    /**
     * 注册的场合
     */
    private String registerLocation;

    /**
     * 门店信息
     */
    private String storeInfo;

    /**
     * 店铺code，当type为branch的时候才有值
     */
    private String branchCode;


    /**
     * 头像
     */
    private String image;

    /**
     * 社区用户名
     */
    private String communityUserName;

    /**
     * 加密
     */
    private Boolean encrypt;

    private Boolean isSubscribe;

    /**
     * 安全密钥
     */
    private String safeKey;

    /**
     * 密钥过期时间
     */
    private Date safeKeyExpireTime;

    /**
     * vip升级
     */
    private Boolean isVipUp;

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Boolean getSubscribe() {
        return isSubscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        isSubscribe = subscribe;
    }

    public Boolean getVipUp() {
        return isVipUp;
    }

    public void setVipUp(Boolean vipUp) {
        isVipUp = vipUp;
    }
}
