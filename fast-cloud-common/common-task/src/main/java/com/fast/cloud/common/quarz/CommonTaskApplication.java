package com.fast.cloud.common.quarz;

import com.fast.cloud.core.swagger.CkSwaggerAutoConfiguration;
import com.fast.cloud.core.test.TestAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@SpringBootApplication(exclude = {CkSwaggerAutoConfiguration.class, TestAutoConfiguration.class})
@EntityScan(basePackages = {"com.fast.cloud.common.quarz.entity"})
@MapperScan("com.fast.cloud.common.quarz.dao")
public class CommonTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonTaskApplication.class, args);
    }

}
