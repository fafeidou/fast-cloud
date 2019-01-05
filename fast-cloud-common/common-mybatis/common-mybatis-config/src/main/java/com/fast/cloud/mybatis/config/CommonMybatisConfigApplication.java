package com.fast.cloud.mybatis.config;

import com.fast.cloud.mybatis.config.mapper.MyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MyMapper.class)})
public class CommonMybatisConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonMybatisConfigApplication.class, args);
    }

}

