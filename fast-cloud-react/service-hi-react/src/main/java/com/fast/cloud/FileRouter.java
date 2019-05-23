package com.fast.cloud;

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
                .route(RequestPredicates.GET("/download2")
                                .and(RequestPredicates.accept(MediaType.IMAGE_PNG)),
                        fileHandler::download2)

                .andRoute(RequestPredicates.POST("/download3")
                                .and(RequestPredicates.accept(MediaType.IMAGE_PNG)),
                        fileHandler::download3)

                .andRoute(RequestPredicates.GET("/hi")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        fileHandler::Hi);
    }
}
