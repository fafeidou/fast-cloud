package com.fast.cloud.dynamic.datasource.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fast.cloud.dynamic.datasource.mysql.annotation.DataSource;
import com.fast.cloud.dynamic.datasource.mysql.service.UserService;
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

import static com.fast.cloud.dynamic.datasource.mysql.enums.DataSourceEnum.DB2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonDynamicDatasourceMysqlApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    UserService userService;

    @Test
    public void selectList() {
        List<UserEntity> userEntities1 = userService.selectList();
        System.out.println();
    }

    @Test
    public void updateUser() {
        UserEntity userEntity = userService.getById("136");
        userEntity.setUsername("修改数据test");
        int i = userService.updateUser(userEntity);
        System.out.println(i);
    }

    @Transactional
    @DataSource(DB2)
    @Test
    public void updateUserBySlave2() {
        List<SysUserEntity> sysUserEntities = sysUserDao.selectList(new QueryWrapper<>());
        System.out.println();
    }
}
