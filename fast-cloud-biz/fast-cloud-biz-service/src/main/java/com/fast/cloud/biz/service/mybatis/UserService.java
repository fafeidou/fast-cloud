package com.fast.cloud.biz.service.mybatis;

import com.fast.cloud.biz.bean.request.UserRequest;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.UserVo;
import com.fast.cloud.biz.domain.UserModel;
import com.fast.cloud.mybatis.bean.request.MyBatisRequest;
import com.fast.cloud.mybatis.service.IService;

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

    CollectionWithPaginationAndAbstractResponse<UserVo> findPage(MyBatisRequest<UserRequest> request);

    List<UserVo> query(MyBatisRequest<UserRequest> request);
}
