package com.fast.cloud.domain.request;



/**
 * @author Batman.qin
 */
public class BaseCommonPaginationSearchRequest<T> extends CommonSearchRequest<T> {

    private PageCondition pagination;

    public PageCondition getPagination() {
        return pagination;
    }

    public void setPagination(PageCondition pagination) {
        this.pagination = pagination;
    }
}
