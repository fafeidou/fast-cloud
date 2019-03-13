package com.fast.cloud.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-03-13 16:10
 */
@FeignClient(value = "service-producer")
public interface HelloClent {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String trace();
}
