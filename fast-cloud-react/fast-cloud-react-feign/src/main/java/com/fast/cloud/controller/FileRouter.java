package com.fast.cloud.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @Author Batman.qin
 * @Date 2019/5/23 13:39
 */
@Configuration
public class FileRouter {
    @Bean
    public RouterFunction<ServerResponse> routeHi(FileHandler fileHandler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/download")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_OCTET_STREAM)),
                        fileHandler::download2);
    }
}
