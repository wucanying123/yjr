package com.pt1002.modules.pojo.query;

import com.pt1002.modules.pojo.CertificationRecord;

import java.util.List;

public class CertificationRecordQuery {
    //总条数
    Long records;
    //当前页数
    Long page;
    //总页数
    Long total;

    public List<CertificationRecord> rows;

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

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public List<CertificationRecord> getRows() {
        return rows;
    }

    public void setRows(List<CertificationRecord> rows) {
        this.rows = rows;
    }
}
