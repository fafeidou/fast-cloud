/*
 * Copyright 2005-2015 CharlesKeith. All rights reserved.
 * Support: http://www.charleskeith.cn
 * License: http://www.charleskeith.cn/license
 */
package com.fast.cloud.mongo.domain.entity;


import com.fast.cloud.mongo.domain.request.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "es_ad")
public class Ad extends BaseEntity<String> {

    private static final long serialVersionUID = -4557771798845228788L;

    /**
     * 标题
     */
    private String title;

    /**
     * 二级标题
     */
    private String titleLower;

    /**
     * 内容
     */
    private String content;
}
