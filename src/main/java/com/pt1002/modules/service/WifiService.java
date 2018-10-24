package com.pt1002.modules.service;

import com.pt1002.modules.pojo.Wifi;
import com.pt1002.modules.pojo.query.WifiQuery;

import java.util.List;

public interface WifiService {
    WifiQuery findWifi(Integer page, Integer rows);

    void deleteWifi(String ids);

    void updateWifi(Wifi wifi);

    List<Wifi> findAll(Long aLong);

    WifiQuery findAll(Long aLong, Integer page, Integer rows);
}
