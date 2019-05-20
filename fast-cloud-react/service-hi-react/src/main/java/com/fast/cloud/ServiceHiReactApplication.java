package com.fast.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
@RestController
@ComponentScan("com.fast.cloud")
public class ServiceHiReactApplication {
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiReactApplication.class, args);
    }

    @RequestMapping("/hello/{name}")
    public Mono<String> sayHiFromClientOne(@PathVariable(name = "name") String name) {
        return Mono.just("hello:" + name + "port:" + environment.getProperty("server.port"));
    }

}
