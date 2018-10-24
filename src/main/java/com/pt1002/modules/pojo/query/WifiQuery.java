package com.pt1002.modules.pojo.query;

import com.pt1002.modules.pojo.Wifi;

import java.util.List;

public class WifiQuery extends BaseQuery {
    private  long page;
    private long records;
    private long total;
    private List<Wifi>  rows;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Wifi> getRows() {
        return rows;
    }

    public void setRows(List<Wifi> rows) {
        this.rows = rows;
    }
}
