/*
 * Copyright 2005-2015 CharlesKeith. All rights reserved.
 * Support: http://www.charleskeith.cn
 * License: http://www.charleskeith.cn/license
 */
package com.fast.cloud.rest.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "es_ad")
public class Ad implements Serializable {
    @Id
    @Indexed
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 二级标题
     */
    private String titleLower;

}
