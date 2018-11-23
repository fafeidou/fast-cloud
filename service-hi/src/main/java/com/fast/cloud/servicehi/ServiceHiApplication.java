package com.fast.cloud.servicehi;

import com.fast.cloud.openapi.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Batman.qin
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceHiApplication implements SchedualServiceHi {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }


    @Value("${server.port}")
    String port;

    @Override
    public String sayHiFromClientOne(String name) {
        return "hi " + name + " ,i am from port:" + port;
    }
}
