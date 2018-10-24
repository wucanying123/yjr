package com.pt1002.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pt1002.common.exceptions.ApplicationException;
import com.pt1002.modules.mapper.*;
import com.pt1002.modules.pojo.*;
import com.pt1002.modules.pojo.query.DevicesQuery;
import com.pt1002.modules.service.DevicesService;
import com.pt1002.util.MD5Util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class DevicesServiceImpl implements DevicesService {
    private static final Logger logger = org.apache.log4j.LogManager.getLogger(Devices.class);
    @Autowired
    DevicesMapper devicesMapper;

    @Autowired
    CertificationRecordMapper recordMapper;

    @Autowired
    TemplatesMapper templatesMapper;

    @Autowired
    DeviceBlackMapper deviceBlackMapper;

    @Autowired
    ImsiMapper imsiMapper;

    @Autowired
    WifiMapper wifiMapper;

    @Override
    public DevicesQuery findPage(Integer page, Integer rows) {


       /* DevicesExample example = new DevicesExample();
        DevicesExample.Criteria criteria = example.createCriteria();
        criteria.andIsActiveEqualTo(DevicesActive.IS_ACTIVE);*/
        PageHelper.startPage(page, rows);
        List<Devices> devices = devicesMapper.selectByExample(null);
        PageInfo<Devices> info = new PageInfo<>(devices);
        DevicesQuery query = new DevicesQuery();
        query.setTotal((int) info.getTotal());
        query.setRows(devices);
        return query;

    }

    @Override
    public int devicesUpdate(Devices devices) {
        return devicesMapper.updateByPrimaryKeySelective(devices);
    }

    @Override
    public int devicesDelete(String ids) {
        String[] split = ids.split(",");
        int deleteCount = 0;
        for (String s : split) {
            Integer value = Integer.valueOf(s);
            List<CertificationRecord> records = recordMapper.selectByDevicesId(value);
            //删除电子围栏的信息
            if (records != null && records.size() > 0) {
                for (CertificationRecord record : records) {
                    imsiMapper.deleteByRecordId(record.getId());
                    wifiMapper.deleteByRecordId(record.getId());
                    //删除认证记录的图片
                    String picturePath = record.getPicturePath();
                    String scenePath = record.getScenePath();
                    if (picturePath != null) {
                        File file = new File(picturePath);
                        if (file.exists()) {
                            file.delete();
                        }
                    } else {
                        logger.warn("没有抓拍图");
                    }

                    if (scenePath != null) {
                        File file = new File(scenePath);
                        if (file.exists()) {
                            file.delete();
                        }
                    } else {
                        logger.warn("场景图也没有");
                    }
                    recordMapper.deleteByPrimaryKey(record.getId());
                }
            }
            DeviceBlackExample deviceBlackExample = new DeviceBlackExample();
            deviceBlackExample.createCriteria().andDeviceIdEqualTo(value);
            deviceBlackMapper.deleteByExample(deviceBlackExample);

            //不删除3D模板
            deleteCount+=devicesMapper.deleteByPrimaryKey(value);
        }
        return deleteCount;
    }

    @Override
    public int devicesAdd(Devices devices) {
        Devices devices1 = devicesMapper.selectBySn(devices.getSn());
        if (devices1 == null) {
            devices.setCreateTime(new Date());
            return devicesMapper.insertSelective(devices);
        } else {
            logger.warn("sn已经存在");
        }
        return 0;
    }

    @Override
    public Devices devicesRegister(String sn,String type) throws ApplicationException {
        Devices devices = null;
        if (sn != null) {
            devices = devicesMapper.selectBySn(MD5Util.getMD5(sn));
            if (devices == null) {
                devices = new Devices();
                devices.setSn(MD5Util.getMD5(sn));
                int length = sn.length();
                if (length == 16) {
                    devices.setName("设备-" + sn.substring(3, 6) + sn.substring(11));
                } else {
                    devices.setName("设备-" + (sn.length()-8<=0?sn:sn.substring(sn.length()-8,sn.length())));
                }
                devices.setLatitude("");
                devices.setLongitude("");
                devices.setStationId("");
                devices.setStationName("");
                devices.setCreateTime(new Date());
                devices.setTypeId(type);
                devices.setCanPush(false);
                devicesMapper.insertSelective(devices);
            } else {
                logger.warn("设备已经注册");
                throw new ApplicationException("设备已经注册");
            }
        }
        return devices;
    }

    @Override
    public List<Map<String, Object>> allDevices() {
        List<Devices> devices = devicesMapper.selectByExample(null);
        List<Map<String, Object>> rs = new ArrayList<>();
        for(Devices item:devices){
            Map<String,Object> map = new HashMap<>();
            map.put("id",item.getId());
            map.put("name",item.getName());
            rs.add(map);
        }
        return rs;
    }

    @Override
    public Devices findBySn(String sn) {
        return devicesMapper.selectBySn(MD5Util.getMD5(sn));
    }
}
