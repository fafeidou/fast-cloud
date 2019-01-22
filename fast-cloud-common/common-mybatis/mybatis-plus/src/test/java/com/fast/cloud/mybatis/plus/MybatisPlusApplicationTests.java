package com.fast.cloud.mybatis.plus;

import com.fast.cloud.mybatis.plus.dao.UserMapper;
import com.fast.cloud.mybatis.plus.domain.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {
    @Autowired
    private UserMapper userDao;

    @Test
    public void insertTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("李四");
        userEntity.setId(UUID.randomUUID().toString());
        Integer i = userDao.insert(userEntity);
        System.err.println("影响行数==>" + i);
    }

    @Test
    public void selectAllTest() {
        List<UserEntity> list = userDao.selectList(null);
        for (UserEntity userEntity:
                list) {
            System.err.println(userEntity);
        }
    }


    @Test
    public void selectByIdTest() {
        UserEntity userEntity = userDao.selectById(1);
        System.err.println(userEntity);
    }

    @Test
    public void deleteTest() {
        int i = userDao.deleteById(1);
        System.err.println("影响行数==>" + i);
        selectAllTest();
    }

    @Test
    public void updateByIdTest() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setUserName("王五");
        int i = userDao.updateById(userEntity);
        System.err.println("影响行数==>" + i);
        selectAllTest();
    }
}