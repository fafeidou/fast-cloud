package com.fast.cloud;

import com.fast.cloud.model.Loggable;
import com.fast.cloud.openapi.SchedualServiceHi;
import com.fast.cloud.openapi.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2018-11-23 19:37
 */
@RestController
public class HiController extends Loggable {


    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/hi")

    public String sayHi(@RequestParam String name) {

        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @GetMapping(value = "/page")
    public PageImpl getPage(@RequestBody User user) {
        return schedualServiceHi.getPage(user);
    }
}
