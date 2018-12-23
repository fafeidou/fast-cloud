package com.fast.cloud.servicehi;

import com.fast.cloud.openapi.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Batman.qin
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@ComponentScan("com.fast.cloud")
public class ServiceHiApplication implements SchedualServiceHi {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }


    @Value("${server.port}")
    String port;

    @Override
    public String sayHiFromClientOne(String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    @Override
    public PageImpl getPage() {
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        return new PageImpl(Arrays.asList("a","b"),pageable,30);
    }
}
