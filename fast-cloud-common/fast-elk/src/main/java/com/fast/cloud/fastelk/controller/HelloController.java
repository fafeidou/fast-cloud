package com.fast.cloud.fastelk.controller;

import com.fast.cloud.fastelk.aspect.SystemLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-03-15 9:33
 */
@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SystemLog
    @RequestMapping("/test")
    public String test(int pageIndex, int pageSize) {
        logger.info("pageIndex ====> {} pageSize ---->{}", pageIndex, pageSize);
        return "hello world";
    }
}
