package com.fast.cloud.mybatis.config;

import com.fast.cloud.mybatis.config.domain.Ad;
import com.fast.cloud.mybatis.config.mapper.AdMapper;
import com.fast.cloud.mybatis.config.service.AdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonMybatisConfigApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(CommonMybatisConfigApplicationTests.class);
    @Autowired
    private AdMapper adMapper;

    @Autowired
    private AdService adService;

    @Test
    public void contextLoads() {
        List<Ad> ads = adMapper.selectAll();
        System.out.println(ads.size());

        // TODO 分页 + 排序 this.userMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<Object> pageInfo = PageHelper.
                startPage(1, 10).
                setOrderBy("id desc").
                doSelectPageInfo(() -> this.adMapper.selectAll());
        log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

        List<Ad> ads1 = adService.selectAll();
        log.info("{size}" + ads1.size());
    }

}

