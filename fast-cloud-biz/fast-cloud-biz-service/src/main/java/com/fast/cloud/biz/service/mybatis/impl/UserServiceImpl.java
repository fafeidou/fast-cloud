package com.fast.cloud.biz.service.mybatis.impl;

import com.fast.cloud.biz.bean.request.CityRequest;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.UserVo;
import com.fast.cloud.biz.domain.UserModel;
import com.fast.cloud.biz.mongo.domain.request.MongoRequest;
import com.fast.cloud.biz.mybatis.config.service.BaseService;
import com.fast.cloud.biz.service.mybatis.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-15 9:50
 */
@Service
public class UserServiceImpl extends BaseService<UserModel> implements UserService {
    @Override
    public Boolean add(UserVo userVo) {
        return null;
    }

    @Override
    public UserVo findById(String id) {
        return null;
    }

    @Override
    public UserVo update(UserVo userVo) {
        return null;
    }

    @Override
    public List<UserVo> queryAll() {
        return null;
    }

    @Override
    public CollectionWithPaginationAndAbstractResponse<UserVo> findPage(MongoRequest<CityRequest> request) {
        return null;
    }

    @Override
    public List<UserVo> query(MongoRequest<CityRequest> request) {
        return null;
    }
}
