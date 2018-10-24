package com.pt1002.modules.pojo.query;

import com.pt1002.modules.pojo.Devices;

import java.util.List;

public class DevicesQuery {

    public Integer total;
    public List<Devices> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Devices> getRows() {
        return rows;
    }

    public void setRows(List<Devices> rows) {
        this.rows = rows;
    }
}
