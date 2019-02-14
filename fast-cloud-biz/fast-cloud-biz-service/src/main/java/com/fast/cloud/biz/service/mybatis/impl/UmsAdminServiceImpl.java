package com.fast.cloud.biz.service.mybatis.impl;

import com.fast.cloud.biz.bean.request.UmsAdminRequest;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.UmsAdminVo;
import com.fast.cloud.biz.domain.UmsAdmin;
import com.fast.cloud.biz.service.mybatis.UmsAdminService;
import com.fast.cloud.mybatis.bean.request.MyBatisRequest;
import com.fast.cloud.mybatis.service.BaseService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

import static java.util.stream.Collectors.toList;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-02-14 13:52
 */
@Service
public class UmsAdminServiceImpl extends BaseService<UmsAdmin> implements UmsAdminService {
    @Override
    public CollectionWithPaginationAndAbstractResponse<UmsAdminVo> findPage(MyBatisRequest<UmsAdminRequest> request) {
        ISelect select = () -> mapper.selectByExample(request.getExample(UmsAdmin.class, null));

        Page<UmsAdmin> userModelPage = PageHelper.startPage(request.getPageNumber(), request.getPageSize())
                .doSelectPage(select);

        CollectionWithPaginationAndAbstractResponse<UmsAdminVo> response = new CollectionWithPaginationAndAbstractResponse<>();
        response.setCurrentPage(request.getPageNumber());
        response.setPageSize(request.getPageSize());
        if (!CollectionUtils.isEmpty(userModelPage.getResult())) {
            response.setDetails(userModelPage.getResult()
                    .stream()
                    .map(i -> {
                                UmsAdminVo umsAdminVo = new UmsAdminVo();
                                BeanUtils.copyProperties(i, umsAdminVo);
                                return umsAdminVo;
                            }
                    ).collect(toList()));
            response.setTotal((int) userModelPage.getTotal());
        } else {
            response.setDetails(Collections.emptyList());
            response.setTotal(0);
        }
        return response;
    }
}
