package com.pt1002.modules.pojo.query;

import com.pt1002.modules.pojo.User;

import java.util.List;

public class UserQuery {
    public Integer total;
    public List<User> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<User> getRows() {
        return rows;
    }

    public void setRows(List<User> rows) {
        this.rows = rows;
    }
}
