package com.pt1002.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pt1002.modules.mapper.*;
import com.pt1002.modules.pojo.*;
import com.pt1002.modules.pojo.query.CertificationRecordQuery;
import com.pt1002.modules.pojo.strong.ElectronicStrong;
import com.pt1002.modules.service.CertificationRecordService;
import com.pt1002.modules.service.UploadRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class CertificationRecordServiceImpl implements CertificationRecordService {
    private static final Logger logger = org.apache.log4j.LogManager.getLogger(CertificationRecord.class);

    @Resource
    CertificationRecordMapper recordMapper;

    @Resource
    PopulationInfoMapper infoMapper;

    @Resource
    DevicesMapper devicesMapper;

    @Resource
    ImsiMapper imsiMapper;

    @Resource
    WifiMapper wifiMapper;

    @Resource
    TemplatesMapper templatesMapper;

    @Resource
    PersonsMapper personsMapper;

    @Autowired
    UploadRecordService uploadRecordService;

    @Override
    public CertificationRecordQuery findPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        PageHelper.orderBy("create_time DESC");
        List<CertificationRecord> records = recordMapper.selectByExample(null);
        if (records!=null && records.size()>0){
            for (CertificationRecord record : records) {
                PopulationInfo populationInfo = infoMapper.selectByPrimaryKey(record.getIdentityId());
                record.setIdentity(populationInfo.getIdentityCard());
                Devices devices = devicesMapper.selectByPrimaryKey(record.getDevicesId().intValue());
                record.setDevicesSn(devices.getSn());
                UploadRecord uploadRecord = uploadRecordService.findUploadRecordByCertId(record.getId());
                record.setState(uploadRecord == null ? 0 : uploadRecord.getState());
            }
        }

        PageInfo<CertificationRecord> info = new PageInfo<>(records);

        CertificationRecordQuery recordQuery = new CertificationRecordQuery();
        recordQuery.setRecords(info.getTotal());
        recordQuery.setPage(Long.valueOf(page));

        recordQuery.setTotal(info.getTotal() % Long.valueOf(rows) == 0 ? info.getTotal() / Long.valueOf(rows) : info.getTotal() / Long.valueOf(rows) + 1);
        recordQuery.setRows(records);
        return recordQuery;
    }

    @Override
    public int addRecord(CertificationRecord record) {
        return recordMapper.insertSelective(record);
    }

    @Override
    public int deleteRecord(String ids) {
        String[] split = ids.split(",");
        TemplatesExample templatesExample = new TemplatesExample();
        TemplatesExample.Criteria exampleCriteria = templatesExample.createCriteria();
        int deleteCount = 0;
        for (String s : split) {
            Long id = Long.valueOf(s);
            //删除电子围栏的信息
            exampleCriteria.andUidEqualTo(s);
            List<TemplatesWithBLOBs> bs = templatesMapper.selectByExampleWithBLOBs(templatesExample);
            if (bs!=null && bs.size()>0){
                for (TemplatesWithBLOBs b : bs) {
                    personsMapper.deleteByPrimaryKey(b.getPid());
                }
            }

            templatesMapper.deleteByExample(templatesExample);
            imsiMapper.deleteByRecordId(id);
            wifiMapper.deleteByRecordId(id);
            //相应的图片也要删除
            CertificationRecord record = recordMapper.selectByPrimaryKey(id);
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

            deleteCount+=recordMapper.deleteByPrimaryKey(id);
        }
        return  deleteCount;
    }

    @Override
    public int updateRecord(CertificationRecord record) {
       return recordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ElectronicStrong> recordHistory(Long id) {

        List<ElectronicStrong> list = new ArrayList<ElectronicStrong>();

        List<Imsi> imsis = imsiMapper.selectByCondition(id,null);

        List<Wifi> wifis = wifiMapper.selectByRecordId(id);

        if (wifis != null && wifis.size() > 0) {
            for (int i = 0; i < wifis.size(); i++) {
                ElectronicStrong strong = new ElectronicStrong();
                if (wifis.size() - 1 >= i) {
                    strong.setWifi(wifis.get(i));
                } else {
                    strong.setWifi(null);
                }
                list.add(strong);
            }
        }

        if (imsis != null && imsis.size() > 0) {
            for (int i = 0; i < imsis.size(); i++) {
                ElectronicStrong strong = new ElectronicStrong();
                if (imsis.size() - 1 >= i) {
                    strong.setImsi(imsis.get(i));
                } else {
                    strong.setImsi(null);
                }
                list.add(strong);
            }
        }

        return list;
    }
}
