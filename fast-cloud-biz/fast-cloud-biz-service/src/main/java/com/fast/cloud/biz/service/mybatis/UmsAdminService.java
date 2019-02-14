package com.fast.cloud.biz.service.mybatis;

import com.fast.cloud.biz.bean.request.UmsAdminRequest;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.fast.cloud.biz.bean.vo.UmsAdminVo;
import com.fast.cloud.biz.domain.UmsAdmin;
import com.fast.cloud.mybatis.bean.request.MyBatisRequest;
import com.fast.cloud.mybatis.service.IService;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-02-14 13:51
 */
public interface UmsAdminService extends IService<UmsAdmin> {
    CollectionWithPaginationAndAbstractResponse<UmsAdminVo> findPage(MyBatisRequest<UmsAdminRequest> request);
}
