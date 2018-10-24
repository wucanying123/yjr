package com.pt1002.modules.pojo.query;

import java.util.List;

public class PopInfoQuery<T> {
    //总条数
    Long  records;
    //当前页数
    Long page;
    //总页数
    Long total;

    List<T> rows;

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
