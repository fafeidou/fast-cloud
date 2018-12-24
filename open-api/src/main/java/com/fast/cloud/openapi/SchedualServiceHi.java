package com.fast.cloud.openapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2018-11-23 19:36
 */
@Api("SchedualServiceHi")
@FeignClient(value = "service-hi", fallback = SchedualServiceHi.SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ApiOperation(value="sayHiFromClientOne", notes="sayHiFromClientOne")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    @ApiOperation(value="getPage", notes="getPage")
    PageImpl<String> getPage(@RequestBody User user);

    @Component
    class SchedualServiceHiHystric implements SchedualServiceHi {
        @Override
        public String sayHiFromClientOne(String name) {
            return "sorry " + name;
        }

        @Override
        public PageImpl<String> getPage(User user) {
            return null;
        }
    }
}
