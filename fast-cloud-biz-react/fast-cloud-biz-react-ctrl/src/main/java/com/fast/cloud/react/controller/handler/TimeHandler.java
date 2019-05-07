package com.fast.cloud.react.controller.handler;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.ok;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-05-06 18:41
 */
@Component
public class TimeHandler {
    public ResponseEntity<Mono<String>> getTime(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("hello world"));
    }
}
