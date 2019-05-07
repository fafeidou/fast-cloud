package com.fast.cloud.biz.bean.vo;

import lombok.Data;

import java.util.Date;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-15 9:24
 */
@Data
public class CityVo {
    private String name;
    private Integer mode;
    private Integer opMode;
    private String franchiseeName;
    private Date openTime;
    private Date updateTime;
    private String sysUserName;
}
