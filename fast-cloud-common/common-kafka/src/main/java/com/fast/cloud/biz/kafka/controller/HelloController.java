package com.fast.cloud.biz.kafka.controller;

import com.fast.cloud.biz.kafka.component.KafkaSender;
import com.fast.cloud.biz.kafka.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.UUID;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-03-14 10:14
 */
@Controller
public class HelloController {
    @Autowired
    private KafkaSender<User> kafkaSender;
    @RequestMapping("test")
    @ResponseBody
    public String test() {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setMsg(UUID.randomUUID().toString());
        user.setSendTime(new Date());
        kafkaSender.send(user);
        return "ok";
    }
}
