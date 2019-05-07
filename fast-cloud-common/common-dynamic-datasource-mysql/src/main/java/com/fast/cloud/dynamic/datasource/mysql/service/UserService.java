package com.fast.cloud.dynamic.datasource.mysql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fast.cloud.dynamic.datasource.mysql.annotation.DataSource;
import com.fast.cloud.mybatis.plus.domain.UserEntity;

import java.util.List;

import static com.fast.cloud.dynamic.datasource.mysql.enums.DataSourceEnum.DB1;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-03-28 9:09
 */
public interface UserService extends IService<UserEntity> {
    @DataSource(DB1)
    List<UserEntity> selectList();

    @DataSource(DB1)
    int updateUser(UserEntity userEntity);
}
