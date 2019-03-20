package com.fast.cloud.mybatis.plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fast.cloud.mybatis.plus.dao.UserMapper;
import com.fast.cloud.mybatis.plus.domain.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {
    @Autowired
    private UserMapper userDao;


    @Test
    public void orderByLambda() {
//        LambdaQueryWrapper<UserEntity> lw = new LambdaQueryWrapper<UserEntity>(UserEntity.class, 1, new HashMap<>());
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.lambda().orderByAsc(UserEntity::getUsername);
        List<UserEntity> userEntities = userDao.selectList(qw);
        System.out.println();
//        Assert.assertTrue(!userDao.s s(lw).isEmpty());
    }

}