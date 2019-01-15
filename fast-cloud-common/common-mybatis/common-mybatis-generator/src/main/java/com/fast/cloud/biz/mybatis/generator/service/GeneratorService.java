package com.fast.cloud.biz.mybatis.generator.service;

import java.io.IOException;

/**
 * @author victor.qin
 * @date 2018/5/24 17:07
 */
public interface GeneratorService {
    byte[] generatorCode(String[] tableNames) throws IOException;
}
