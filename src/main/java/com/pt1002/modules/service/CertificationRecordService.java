package com.pt1002.modules.service;

import com.pt1002.modules.pojo.CertificationRecord;
import com.pt1002.modules.pojo.query.CertificationRecordQuery;
import com.pt1002.modules.pojo.strong.Electronic;
import com.pt1002.modules.pojo.strong.ElectronicStrong;

import java.util.List;

public interface CertificationRecordService {
    CertificationRecordQuery findPage(Integer page, Integer rows);

    int addRecord(CertificationRecord record);

    int deleteRecord(String ids);

    int updateRecord(CertificationRecord record);

    List<ElectronicStrong> recordHistory(Long id);
}
