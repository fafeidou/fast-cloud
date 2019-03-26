package com.fast.cloud.common.quarz;

import com.fast.cloud.core.swagger.CkSwaggerAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = CkSwaggerAutoConfiguration.class)
public class CommonTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonTaskApplication.class, args);
    }

}
