package com.fast.cloud.biz.file.controller;

import com.fast.cloud.biz.openapi.FileUploadFeignClient;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FeignUploadController implements FileUploadFeignClient {

    @Override
    public Mono<String> fileUpload(@RequestPart(value = "file") FilePart file) {
        return Mono.just(file.filename());
    }
}
