package com.fast.cloud.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@RestController
public class ServiceComsumerApplication {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HelloClent helloClent;

    public static void main(String[] args) {
        SpringApplication.run(ServiceComsumerApplication.class, args);
    }

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String trace() {
        logger.info("trace consumer");
        return helloClent.trace();
    }
}
