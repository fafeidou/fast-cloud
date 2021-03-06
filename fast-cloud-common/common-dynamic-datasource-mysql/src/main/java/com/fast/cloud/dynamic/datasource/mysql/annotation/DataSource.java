/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.fast.cloud.dynamic.datasource.mysql.annotation;

import com.fast.cloud.dynamic.datasource.mysql.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 * @since 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    DataSourceEnum value() default DataSourceEnum.DB1;
}
