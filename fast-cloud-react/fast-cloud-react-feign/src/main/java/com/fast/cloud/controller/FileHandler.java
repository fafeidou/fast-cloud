package com.fast.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @Author Batman.qin
 * @Date 2019/5/23 13:38
 */
@Component
public class FileHandler {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<ServerResponse> download2(ServerRequest request) {
        return webClientBuilder.baseUrl("http://SERVICE-HI-REACT/")
                .build()
                .post()
                .uri("download3")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .retrieve()
                .bodyToMono(ServerResponse.class);
    }
}
