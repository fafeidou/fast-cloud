package com.fast.cloud.biz.controller;

import com.fast.cloud.biz.bean.request.CityRequest;
import com.fast.cloud.biz.bean.response.ApiResponse;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.CityVo;
import com.fast.cloud.biz.service.mongo.CityService;
import com.fast.cloud.mongo.bean.request.MongoRequest;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-14 18:03
 */
@RestController
public class CityController {
    public static final String ROOT = "admin/city/";

    @Autowired
    private CityService cityService;

    @RequestMapping(value = ROOT + "add", method = RequestMethod.POST)
    public ApiResponse<Boolean> add(CityVo cityVo) {
        return ApiResponse.success(cityService.add(cityVo));
    }

    @RequestMapping(value = ROOT + "get/{id}", method = RequestMethod.GET)
    public ApiResponse<CityVo> get(String id) {
        return ApiResponse.success(cityService.findById(id));
    }

    @RequestMapping(value = ROOT + "update", method = RequestMethod.POST)
    public ApiResponse<CityVo> update(CityVo cityVo) {
        return ApiResponse.success(cityService.update(cityVo));
    }

    @RequestMapping(value = ROOT + "remove/{ids}", method = RequestMethod.GET)
    public ApiResponse<Boolean> remove(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            cityService.delete(ids.split(","));
        }
        return ApiResponse.success(true);
    }

    @RequestMapping(value = ROOT + "/all", method = RequestMethod.GET)
    public ApiResponse<List<CityVo>> list() {
        return ApiResponse.success(cityService.queryAll());
    }

    @RequestMapping(value = ROOT + "/findPage")
    public ApiResponse<CollectionWithPaginationAndAbstractResponse<CityVo>> findPage(@RequestBody @Valid MongoRequest<CityRequest> request, BindingResult result) {
        return ApiResponse.success(cityService.findPage(request));
    }

    @RequestMapping(value = ROOT + "find")
    public ApiResponse<List<CityVo>> find(@RequestBody MongoRequest<CityRequest> request) {
        return ApiResponse.success(cityService.query(request));
    }

    @RequestMapping(value = ROOT + "test")
    public ApiResponse<List<CityVo>> test(@RequestBody @Valid CityRequest request, BindingResult result) {
        return null;
    }
}
