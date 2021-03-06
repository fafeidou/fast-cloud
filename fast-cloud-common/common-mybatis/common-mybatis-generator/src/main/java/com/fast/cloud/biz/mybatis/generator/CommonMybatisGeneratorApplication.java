package com.fast.cloud.biz.mybatis.generator;

import com.fast.cloud.biz.mybatis.generator.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileOutputStream;

@SpringBootApplication
public class CommonMybatisGeneratorApplication implements CommandLineRunner {

    @Autowired
    private GeneratorService generatorService;

    public static void main(String[] args) {
        SpringApplication.run(CommonMybatisGeneratorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        byte[] com_codes = generatorService.generatorCode(new String[]{"ums_admin","ums_role","ums_permission",
                "ums_admin_permission_relation","ums_admin_role_relation","ums_role_permission_relation"});
        IOUtils.write(com_codes, new FileOutputStream(new File("C:\\test\\test.zip")));
    }
}

