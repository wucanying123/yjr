package com.pt1002.modules.service;

import com.pt1002.modules.pojo.Imsi;
import com.pt1002.modules.pojo.query.ImsiQuery;

import java.util.List;

public interface ImsiService {
    ImsiQuery findImsi(Integer page, Integer rows);

    int deleteImsi(String ids);

    int updateImsi(Imsi imsi);

    List<Imsi> findAll(Long aLong);

    ImsiQuery findAll(Long aLong, String imsi, Integer page, Integer rows);
}
