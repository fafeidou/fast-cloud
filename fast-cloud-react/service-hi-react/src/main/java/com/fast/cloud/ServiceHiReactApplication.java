package com.fast.cloud;

import com.fast.cloud.domain.ComRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@Controller
@ComponentScan("com.fast.cloud")
public class ServiceHiReactApplication extends BaseController {
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiReactApplication.class, args);
    }

    @RequestMapping("/hello/{name}")
    @ResponseBody
    public Mono<String> sayHiFromClientOne(@PathVariable(name = "name") String name) {
        return Mono.just("hello:" + name + "port:" + environment.getProperty("server.port"));
    }

    @RequestMapping("/hello2")
    @ResponseBody
    public Mono<String> hello2(@RequestParam("sort") String sortField, @RequestParam("direction") String sortDirection) {
        return Mono.just("sortField:" + sortField + "sortDirection:" + sortDirection);
    }

    @RequestMapping("/hello3")
    @ResponseBody
    public Mono<String> hello3(@RequestBody ComRequest comRequest) {
        return Mono.just("comRequest.getName():" + comRequest.getName());
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    Mono<String> upload(@RequestPart(value = "file") FilePart file) {
        return Mono.just(file.filename());
    }

    @GetMapping(value = "/download")
    Mono<Void> download(ServerHttpResponse response) throws IOException {
        ZeroCopyHttpOutputMessage zeroCopyResponse = (ZeroCopyHttpOutputMessage) response;
        response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=parallel.png");
        response.getHeaders().setContentType(MediaType.IMAGE_PNG);

        Resource resource = new ClassPathResource("images.jpg");
        File file = resource.getFile();
        return zeroCopyResponse.writeWith(file, 0, file.length());
    }

//    @GetMapping(value = "/download2")
//    @ResponseBody
//    Mono<ServerResponse> download2(ServerHttpResponse response) throws IOException {
////        ZeroCopyHttpOutputMessage zeroCopyResponse = (ZeroCopyHttpOutputMessage) response;
////        response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=parallel.png");
////        response.getHeaders().setContentType(MediaType.IMAGE_PNG);
////
////        Resource resource = new ClassPathResource("images.jpg");
////        File file = resource.getFile();
////        return zeroCopyResponse.writeWith(file, 0, file.length());
//        Resource resource = new ClassPathResource("images.jpg");
//        File file = resource.getFile();
//        return ServerResponse.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=parallel.png")
//                .contentType(MediaType.IMAGE_PNG)
//                .body(BodyInserters.fromResource(resource))
//                .switchIfEmpty(Mono.empty());
//    }

//    public Mono<ServerResponse> test(ServerRequest request) throws Exception {
//        File excel = new File("tmp");
//        var out = new FileOutputStream(excel);
//        var writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,false);
//        Sheet sheet1 = new Sheet(1, 0);
//        sheet1.setSheetName("第一个sheet");
//        writer.write(Arrays.asList(), sheet1);
//        writer.finish();
//        return ServerResponse.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=test.xlsx")
//                .contentType(new MediaType("multipart/form-data"))
//                .body((p, a) -> {
//                    var resp = (ZeroCopyHttpOutputMessage) p;
//                    return resp.writeWith(excel, 0, excel.length());
//                }).doFinally(a -> {excel.deleteOnExit();});
//    }

}
