package com.pt1002.modules.pojo.query;

import com.pt1002.modules.pojo.Devices;
import com.pt1002.modules.pojo.Persons;

import java.util.List;

public class PersonsQuery {

    public Integer total;
    public List<Persons> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Persons> getRows() {
        return rows;
    }

    public void setRows(List<Persons> rows) {
        this.rows = rows;
    }
}
