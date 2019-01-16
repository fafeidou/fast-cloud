package com.fast.cloud.biz.service.mongo;

import com.fast.cloud.biz.bean.request.CityRequest;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.CityVo;
import com.fast.cloud.biz.domain.CityModel;
import com.fast.cloud.mongo.bean.request.MongoRequest;
import com.fast.cloud.mongo.service.IService;

import java.util.List;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-14 17:38
 */
public interface CityService extends IService<CityModel, String> {
    Boolean add(com.fast.cloud.biz.bean.vo.CityVo cityVo);

    CityVo findById(String id);

    CityVo update(CityVo cityVo);

    List<CityVo> queryAll();

    CollectionWithPaginationAndAbstractResponse<CityVo> findPage(MongoRequest<CityRequest> request);

    List<CityVo> query(MongoRequest<CityRequest> request);
}
