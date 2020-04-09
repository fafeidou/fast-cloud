package com.fast.cloud.biz.redis.aop;

/**
 * @Classname LockDistributed
 * @Description TODO
 * @Date 2020/4/8 11:46
 * @Created by qinfuxiang
 */

import java.lang.annotation.*;

/**
 * @author qinfuxiang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockDistributed {

    String value() default "";

    int time() default 30;
}


