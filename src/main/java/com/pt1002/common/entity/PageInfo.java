package com.pt1002.common.entity;


import com.pt1002.common.enums.OrderType;

public class PageInfo {

    private int page = 1;

    private int pageSize = 10;

    private String sort;

    private OrderType order = OrderType.DESC;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public OrderType getOrder() {
        return order;
    }

    public void setOrder(OrderType order) {
        this.order = order;
    }
}
