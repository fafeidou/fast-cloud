package com.fast.cloud.mybatis.config;

import com.fast.cloud.mybatis.mapper.MyMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MyMapper.class)})
public class CommonMybatisConfig {
}

