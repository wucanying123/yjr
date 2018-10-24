package com.pt1002.modules.service;

import com.pt1002.common.exceptions.ApplicationException;
import com.pt1002.modules.pojo.Devices;
import com.pt1002.modules.pojo.query.DevicesQuery;

import java.util.List;
import java.util.Map;

public interface DevicesService {

    DevicesQuery findPage(Integer page, Integer rows);

    int devicesUpdate(Devices devices);

    int devicesDelete(String ids);

    int devicesAdd(Devices devices);

    Devices devicesRegister(String sn,String type) throws ApplicationException;

    List<Map<String,Object>> allDevices();

    Devices findBySn(String sn);
}
