package com.fast.cloud.biz.service;

import com.fast.cloud.biz.bean.request.PageCondition;
import com.fast.cloud.biz.bean.response.CollectionWithPaginationAndAbstractResponse;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2019-01-14 17:04
 */
public abstract class BizBaseService {

    public static <BEAN> CollectionWithPaginationAndAbstractResponse<BEAN> baseMongo(Integer total,
                                                                                     PageCondition page,
                                                                                     List<BEAN> details) {
        CollectionWithPaginationAndAbstractResponse<BEAN> result =
                new CollectionWithPaginationAndAbstractResponse<>();
        if (CollectionUtils.isEmpty(details)) {
            result.setDetails(details);
        } else {
            result.setDetails(Lists.newArrayList());
        }
        result.setTotal(total);
        result.setDetails(details);
        return result;
    }

}
