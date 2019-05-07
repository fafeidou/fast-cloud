package com.fast.cloud.biz.bean.request;

import com.fast.cloud.core.bean.MatchType;
import com.fast.cloud.core.bean.QueryCondition;
import lombok.Data;

/**
 *
 *
 * @author Batman.qin
 * @create 2019-01-15 9:53
 */
@Data
public class UserRequest {
    private String id;
    @QueryCondition
    private String userName;
    @QueryCondition(func = MatchType.like)
    private String password;
}
