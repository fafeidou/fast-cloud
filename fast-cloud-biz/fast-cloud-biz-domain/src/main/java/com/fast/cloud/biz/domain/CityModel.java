package com.fast.cloud.biz.domain;

import com.fast.cloud.bean.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-14 17:17
 */
@Data
@Document("city")
public class CityModel extends BaseEntity {
    private String name;
    private Integer mode;
    private Integer opMode;
    private String franchiseeName;
    private Date openTime;
    private Date updateTime;
    private String sysUserName;

}
