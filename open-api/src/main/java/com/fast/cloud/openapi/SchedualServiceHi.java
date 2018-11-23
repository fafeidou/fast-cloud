package com.fast.cloud.openapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2018-11-23 19:36
 */
@FeignClient(value = "service-hi", fallback = SchedualServiceHi.SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    @Component
    class SchedualServiceHiHystric implements SchedualServiceHi {
        @Override
        public String sayHiFromClientOne(String name) {
            return "sorry " + name;
        }
    }
}
