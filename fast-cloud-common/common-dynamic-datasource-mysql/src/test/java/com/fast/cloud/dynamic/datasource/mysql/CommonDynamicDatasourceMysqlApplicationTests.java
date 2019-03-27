package com.fast.cloud.dynamic.datasource.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fast.cloud.dynamic.datasource.mysql.annotation.DataSource;
import com.fast.cloud.mybatis.plus.dao.SysUserDao;
import com.fast.cloud.mybatis.plus.dao.UserMapper;
import com.fast.cloud.mybatis.plus.domain.SysUserEntity;
import com.fast.cloud.mybatis.plus.domain.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.fast.cloud.dynamic.datasource.mysql.enums.DataSourceEnum.DB1;
import static com.fast.cloud.dynamic.datasource.mysql.enums.DataSourceEnum.DB2;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonDynamicDatasourceMysqlApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    SysUserDao sysUserDao;

    @Transactional
    @DataSource(DB1)
    @Test
    public void updateUserBySlave1() {
        List<UserEntity> userEntities = userMapper.selectList(new QueryWrapper<>());
        System.out.println();
    }

    @Transactional
    @DataSource(DB2)
    @Test
    public void updateUserBySlave2() {
        List<SysUserEntity> sysUserEntities = sysUserDao.selectList(new QueryWrapper<>());
        System.out.println();
    }
}
