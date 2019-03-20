package com.fast.cloud.mybatis.plus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fast.cloud.mybatis.plus.domain.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-17 15:10
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
