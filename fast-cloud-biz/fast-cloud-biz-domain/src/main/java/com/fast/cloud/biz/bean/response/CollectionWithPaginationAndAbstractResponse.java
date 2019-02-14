package com.fast.cloud.biz.bean.response;


/**
 * @author Batman.qin
 */
public class CollectionWithPaginationAndAbstractResponse<T>
        extends CollectionWithAbstractResponse<T> {
    private Integer total;
    private Integer currentPage;
    private Integer pageSize;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
