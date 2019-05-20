package com.fast.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Batman.qin
 * @create 2018-11-23 19:37
 */
@RestController
public class HiController {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping(value = "/hi")
    public Mono<String> sayHi(@RequestParam String name) {
        return webClientBuilder.baseUrl("http://SERVICE-HI-REACT/").build()
                .get().uri("/hello/{name}","aaa")
                .retrieve()
                .bodyToMono(String.class);
    }

}
