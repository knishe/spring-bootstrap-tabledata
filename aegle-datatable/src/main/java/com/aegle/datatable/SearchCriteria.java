package com.aegle.datatable;

/**
 * @author Nishanthan Krishnakumar
 * @version 1.0
 * @since 1.0
 */
public class SearchCriteria {
    private String sort;
    private String order;
    private int offset;
    private int limit;
    private String filter;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
