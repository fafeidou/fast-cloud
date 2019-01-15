package com.fast.cloud.biz.bean.response;


/**
 * @author Batman.qin
 */
public class CollectionWithPaginationAndAbstractResponse<T>
        extends CollectionWithAbstractResponse<T> {
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
