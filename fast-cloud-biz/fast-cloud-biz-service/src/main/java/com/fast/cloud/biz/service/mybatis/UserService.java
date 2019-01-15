package com.fast.cloud.biz.service.mybatis;

import com.fast.cloud.biz.bean.request.CityRequest;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.UserVo;
import com.fast.cloud.biz.domain.UserModel;
import com.fast.cloud.biz.mongo.domain.request.MongoRequest;
import com.fast.cloud.biz.mybatis.config.service.IService;

import java.util.List;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-15 9:49
 */
public interface UserService extends IService<UserModel> {
    Boolean add(UserVo userVo);

    UserVo findById(String id);

    UserVo update(UserVo userVo);

    List<UserVo> queryAll();

    CollectionWithPaginationAndAbstractResponse<UserVo> findPage(MongoRequest<CityRequest> request);

    List<UserVo> query(MongoRequest<CityRequest> request);
}
