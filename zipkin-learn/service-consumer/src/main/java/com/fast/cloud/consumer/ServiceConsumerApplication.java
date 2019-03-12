package com.fast.cloud.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ServiceConsumerApplication {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {

        SpringApplication.run(ServiceConsumerApplication.class, args);
    }

    @Autowired
    HiClient client;

    @GetMapping(value = "/hi")
    public String sayHi() {
        logger.info("trace consumer");
        return client.trace();
    }

}
