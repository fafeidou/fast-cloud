package com.fast.cloud.biz.service.mongo.impl;

import com.fast.cloud.biz.bean.request.CityRequest;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.CityVo;
import com.fast.cloud.biz.domain.CityModel;
import com.fast.cloud.mongo.bean.request.MongoRequest;
import com.fast.cloud.mongo.service.BaseService;
import com.fast.cloud.biz.service.mongo.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-14 17:38
 */
@Service
public class CityServiceImpl extends BaseService<CityModel, String> implements CityService {

    @Override
    public Boolean add(CityVo cityVo) {
        return null;
    }

    @Override
    public CityVo findById(String id) {
        return null;
    }

    @Override
    public CityVo update(CityVo cityVo) {
        return null;
    }

    @Override
    public List<CityVo> queryAll() {
        return null;
    }

    @Override
    public CollectionWithPaginationAndAbstractResponse<CityVo> findPage(MongoRequest<CityRequest> request) {
        return null;
    }

    @Override
    public List<CityVo> query(MongoRequest<CityRequest> request) {
        return null;
    }
}
