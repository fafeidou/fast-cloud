package com.fast.cloud.mongo.domain.request;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * COPYRIGHT © 2005-2018 CHARLESKEITH ALL RIGHTS RESERVED.
 *
 * @author Batman.qin
 * @create 2018-11-20 14:51
 */
public abstract class AbstractRequest<T> implements Serializable, Pageable {
    protected Integer pageNumber = 1;
    protected Integer pageSize = 10;
    protected Sort sort;
    protected T requestModel;
    protected String sortName;
    protected int sortOrder;

    public T getRequestModel() {
        return requestModel;
    }

    public void setRequestModel(T requestModel) {
        this.requestModel = requestModel;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return (getPageNumber() - 1) * getPageSize();
    }

    @Override
    public Sort getSort() {
        if (sortName != null && !"".equals(sortName)) {
            return new Sort(new Sort.Order(sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortName));
        }
        return sort;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
