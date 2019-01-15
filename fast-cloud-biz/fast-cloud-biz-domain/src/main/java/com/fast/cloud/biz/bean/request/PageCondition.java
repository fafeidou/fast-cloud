package com.fast.cloud.biz.bean.request;

import java.io.Serializable;


/**
 * @author Batman.qin
 */
public class PageCondition implements Serializable {
    private Integer page;
    private Integer size;
    private boolean allInOne;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isAllInOne() {
        return allInOne;
    }

    public void setAllInOne(boolean allInOne) {
        this.allInOne = allInOne;
    }

    @Override
    public String toString() {
        return "PageCondition{" +
                "page=" + page +
                ",size=" + size +
                ",allInOne=" + allInOne +
                '}';
    }
}
