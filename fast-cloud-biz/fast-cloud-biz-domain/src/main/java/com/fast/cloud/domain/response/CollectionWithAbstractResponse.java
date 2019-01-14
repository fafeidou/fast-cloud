package com.fast.cloud.domain.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Batman.qin
 */
public class CollectionWithAbstractResponse<T, U> implements Serializable {
    private List<T> details;
    private U summary;

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }

    public U getSummary() {
        return summary;
    }

    public void setSummary(U summary) {
        this.summary = summary;
    }
}
