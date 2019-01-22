package com.fast.cloud.biz.servicehi;

import com.fast.cloud.biz.openapi.SchedualServiceHi;
import com.fast.cloud.biz.openapi.User;
import com.fast.cloud.biz.model.Loggable;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author Batman.qin
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@RestController
@ComponentScan("com.fast.cloud")
public class ServiceHiApplication extends Loggable implements SchedualServiceHi {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @Override
    @CacheResult
    @HystrixCommand
    public String sayHiFromClientOne(String name) {
        $info("----------------" + name);
        return "hi " + name + " ,i am from port:" + port;
    }

    @Override
    public PageImpl<String> getPage(User user) {
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
        return new PageImpl(Arrays.asList("a", "b"), pageable, 30);
    }
}
