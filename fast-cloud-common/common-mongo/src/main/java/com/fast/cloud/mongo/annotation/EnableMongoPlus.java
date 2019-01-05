package com.fast.cloud.mongo.annotation;

import com.fast.cloud.mongo.config.MongoPlusAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-03 17:05
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({MongoPlusAutoConfiguration.class})
public @interface EnableMongoPlus {

}
