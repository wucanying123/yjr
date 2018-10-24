package com.pt1002.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pt1002.modules.mapper.WifiMapper;
import com.pt1002.modules.pojo.Wifi;
import com.pt1002.modules.pojo.query.WifiQuery;
import com.pt1002.modules.service.WifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class WifiServiceImpl implements WifiService {

    @Autowired
    WifiMapper wifiMapper;
    @Override
    public WifiQuery findWifi(Integer page, Integer rows) {

        PageHelper.startPage(page,rows);
        List<Wifi> wifis = wifiMapper.selectByAll();
        PageInfo<Wifi> info = new PageInfo<Wifi>(wifis);
        WifiQuery wifiQuery = new WifiQuery();
        wifiQuery.setTotal(info.getTotal());
        wifiQuery.setRows(info.getList());
        return wifiQuery;
    }

    @Override
    public void deleteWifi(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Integer id = Integer.valueOf(s);
            wifiMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void updateWifi(Wifi wifi) {

        wifiMapper.updateByPrimaryKey(wifi);
    }

    @Override
    public List<Wifi> findAll(Long recordId) {
        List<Wifi> wifis = wifiMapper.selectByRecordId(recordId);
        return wifis;
    }

    @Override
    public WifiQuery findAll(Long recordId, Integer page, Integer rows) {
        if (page==null)
            page=1;
        if (rows == null)
            rows=10;
        PageHelper.startPage(page,rows);
        List<Wifi> wifis = wifiMapper.selectByRecordId(recordId);
        PageInfo<Wifi> info = new PageInfo<Wifi>(wifis);
        WifiQuery wifiQuery = new WifiQuery();
        wifiQuery.setPage(page);
        wifiQuery.setRecords(info.getTotal());
        wifiQuery.setTotal(info.getTotal()%Long.valueOf(rows)==0?info.getTotal()/Long.valueOf(rows):info.getTotal()/Long.valueOf(rows)+1);
        wifiQuery.setRows(info.getList());
        return wifiQuery;

    }
}
