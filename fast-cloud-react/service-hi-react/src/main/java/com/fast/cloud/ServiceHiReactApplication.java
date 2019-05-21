package com.fast.cloud;

import com.fast.cloud.domain.ComRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientResponse;
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

    @RequestMapping("/hello2")
    public Mono<String> hello2(@RequestParam("sort") String sortField, @RequestParam("direction") String sortDirection) {
        return Mono.just("sortField:" + sortField + "sortDirection:" + sortDirection);
    }

    @RequestMapping("/hello3")
    public Mono<String> hello3(@RequestBody ComRequest comRequest) {
        return Mono.just("comRequest.getName():" + comRequest.getName());
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Mono<String> upload(@RequestPart(value = "file") FilePart file) {
        return Mono.just(file.filename());
    }

    @GetMapping(value = "/download")
    Mono<ClientResponse> download() {
        return Mono.just(null);
    }

}
