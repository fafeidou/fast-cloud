package com.fast.cloud.biz.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Batman.qin
 */
public class CollectionWithAbstractResponse<T> implements Serializable {
    private List<T> details;

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }
}
