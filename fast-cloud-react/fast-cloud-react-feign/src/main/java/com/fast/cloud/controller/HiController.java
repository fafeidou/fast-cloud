package com.fast.cloud.controller;

import com.fast.cloud.domain.ComRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
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
        return webClientBuilder.baseUrl("http://SERVICE-HI-REACT/")
                .build()
                .get().uri("/hello/{name}", "aaa")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping(value = "/hello2")
    public Mono<String> hello2(String sortField, String sortDirection) {
        return webClientBuilder.baseUrl("http://SERVICE-HI-REACT/")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/hello2")
                        .queryParam("sort", "updated")
                        .queryParam("direction", "desc").build())
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping(value = "/hello3")
    public Mono<String> hello2(@RequestBody ComRequest comRequest) {
        return webClientBuilder.baseUrl("http://SERVICE-HI-REACT/")
                .build()
                .post().uri("hello3")
                .syncBody(comRequest)
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Mono<String> upload(@RequestPart("file") MultipartFile file) {
        return webClientBuilder.baseUrl("http://SERVICE-HI-REACT/")
                .build()
                .post().uri("upload").contentType(MediaType.MULTIPART_FORM_DATA)
                .syncBody(BodyInserters.fromMultipartData("file", file))
                .retrieve()
                .bodyToMono(String.class);
    }

//    @GetMapping(value = "/download")
//    Mono<ClientResponse> download() {
//        Mono<ClientResponse> resp = webClientBuilder.baseUrl("http://SERVICE-HI-REACT/")
//                .build()
//                .post()
//                .uri("download3")
//                .accept(MediaType.IMAGE_PNG)
//                .exchange();
//        return resp;
//    }
}
