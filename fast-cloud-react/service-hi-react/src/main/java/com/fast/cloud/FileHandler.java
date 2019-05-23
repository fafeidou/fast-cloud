package com.fast.cloud;

import lombok.var;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

/**
 * @Author Batman.qin
 * @Date 2019/5/23 13:38
 */
@Component
public class FileHandler {
    public Mono<ServerResponse> download2(ServerRequest response) {
        Resource resource = new ClassPathResource("images.jpg");
        return ServerResponse.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=parallel.png")
                .contentType(MediaType.IMAGE_PNG)
                .body(BodyInserters.fromResource(resource))
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ServerResponse> download3(ServerRequest request) {
        Resource resource = new ClassPathResource("images.jpg");
        try {
            File file = resource.getFile();
            return ServerResponse.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=parallel.png")
                    .contentType(MediaType.IMAGE_PNG)
                    .body((p, a) -> {
                        var resp = (ZeroCopyHttpOutputMessage) p;
                        if (file == null) {

                        }
                        return resp.writeWith(file, 0, file.length());
                    }).doFinally(a -> file.deleteOnExit());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.ok().build();

    }

    public Mono<ServerResponse> Hi(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject("Hi , this is SpringWebFlux"));
    }
}
