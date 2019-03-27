package com.fast.cloud.dynamic.datasource.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CommonDynamicDatasourceMysqlApplication implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public static void main(String[] args) {
        SpringApplication.run(CommonDynamicDatasourceMysqlApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<Map<String, Object>> maps2 = jdbcTemplate.queryForList("select * from schedule_job");
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from salary");
        System.out.println();
    }
}
