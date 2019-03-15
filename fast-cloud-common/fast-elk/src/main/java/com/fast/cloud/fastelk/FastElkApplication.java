package com.fast.cloud.fastelk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FastElkApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(FastElkApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("testte");
        logger.info("sdfsdfsadfsadfsadf");
    }
}
