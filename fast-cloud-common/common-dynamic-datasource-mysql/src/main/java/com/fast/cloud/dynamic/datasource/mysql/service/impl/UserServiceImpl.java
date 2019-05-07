package com.fast.cloud.dynamic.datasource.mysql.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fast.cloud.dynamic.datasource.mysql.annotation.DataSource;
import com.fast.cloud.dynamic.datasource.mysql.service.UserService;
import com.fast.cloud.mybatis.plus.dao.UserMapper;
import com.fast.cloud.mybatis.plus.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

import static com.fast.cloud.dynamic.datasource.mysql.enums.DataSourceEnum.DB1;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-03-28 9:17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    @DataSource(DB1)
    public List<UserEntity> selectList() {
        List<UserEntity> userEntities = baseMapper.selectList(new QueryWrapper<>());
        return userEntities;
    }

    @Override
    @DataSource(DB1)
    public int updateUser(UserEntity userEntity) {
        return baseMapper.updateById(userEntity);
    }

    @Override
    @DataSource(DB1)
    public UserEntity getById(Serializable id) {
        return super.getById(id);
    }
}
