package com.fast.cloud.biz.file.controller;

import com.fast.cloud.biz.openapi.FileUploadFeignClient;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FeignUploadController implements FileUploadFeignClient {

    @Override
    public String fileUpload(@RequestPart(value = "file") MultipartFile file) {
        return file.getOriginalFilename();
    }
}