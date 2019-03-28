package com.fast.cloud.dynamic.datasource.mysql;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.fast.cloud.mybatis.plus.dao")
public class CommonDynamicDatasourceMysqlApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CommonDynamicDatasourceMysqlApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }

    /**
     * 乐观锁插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
