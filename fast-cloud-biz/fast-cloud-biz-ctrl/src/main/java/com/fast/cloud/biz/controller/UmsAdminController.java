package com.fast.cloud.biz.controller;

import com.fast.cloud.biz.bean.request.UmsAdminRequest;
import com.fast.cloud.biz.bean.response.ApiResponse;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.UmsAdminVo;
import com.fast.cloud.biz.service.mybatis.UmsAdminService;
import com.fast.cloud.mybatis.bean.request.MyBatisRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-02-14 13:53
 */
@RestController
public class UmsAdminController {
    public static final String ROOT = "admin/umsAdmin/";
    @Autowired
    private UmsAdminService umsAdminService;

    @RequestMapping(value = ROOT + "/findPage")
    public ApiResponse<CollectionWithPaginationAndAbstractResponse<UmsAdminVo>> findPage(@RequestBody MyBatisRequest<UmsAdminRequest> request) {
        return ApiResponse.success(umsAdminService.findPage(request));
    }
}
