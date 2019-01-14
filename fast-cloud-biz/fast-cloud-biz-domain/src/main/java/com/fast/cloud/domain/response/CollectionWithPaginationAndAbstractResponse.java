package com.fast.cloud.domain.response;


/**
 * @author Batman.qin
 */
public class CollectionWithPaginationAndAbstractResponse<T, U>
        extends CollectionWithAbstractResponse<T, U> {
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
