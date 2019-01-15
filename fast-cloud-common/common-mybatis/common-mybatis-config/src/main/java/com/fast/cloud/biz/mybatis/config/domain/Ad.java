package com.fast.cloud.biz.mybatis.config.domain;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-03 11:44
 */
@Data
@Table(name = "es_ad")
public class Ad implements Serializable {
    private String id;
    private String title;
}
