package com.fast.cloud.biz.controller;

import com.fast.cloud.biz.model.Loggable;
import com.fast.cloud.biz.openapi.SchedualServiceHi;
import com.fast.cloud.biz.openapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

/**
 *
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

    @RequestMapping(value = "/page")
    public PageImpl getPage(@RequestBody User user) {
        return schedualServiceHi.getPage(user);
    }
}
