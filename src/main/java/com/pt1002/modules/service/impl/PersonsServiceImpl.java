package com.pt1002.modules.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pt1002.modules.mapper.*;
import com.pt1002.modules.pojo.CertificationRecord;
import com.pt1002.modules.pojo.Persons;
import com.pt1002.modules.pojo.PopulationInfo;
import com.pt1002.modules.pojo.query.PersonsQuery;
import com.pt1002.modules.service.PersonsService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PersonsServiceImpl implements PersonsService {

    private static final Logger logger = LogManager.getLogger(PopulationInfo.class);


    @Autowired
    TemplatesMapper templatesMapper;

    @Autowired
    PopulationInfoMapper populationInfoMapper;

    @Autowired
    CertificationRecordMapper recordMapper;

    @Autowired
    ImsiMapper imsiMapper;

    @Autowired
    WifiMapper wifiMapper;

    @Autowired
    PersonsMapper personsMapper;

    @Override
    public PersonsQuery findPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Persons> persons = personsMapper.selectByExample(null);
        PageInfo<Persons> pageInfo = new PageInfo<>(persons);
        PersonsQuery query = new PersonsQuery();
        query.setTotal((int) pageInfo.getTotal());
        query.setRows(persons);
        return query;
    }

    @Override
    public void personsDelete(String ids) {

        String[] split = ids.split(",");
        for (String s : split) {
            Integer id = Integer.valueOf(s);
            Persons persons = personsMapper.selectByPrimaryKey(id);
            PopulationInfo populationInfo = populationInfoMapper.selectByPrimaryKey(Long.valueOf(persons.getCardId()));
            List<CertificationRecord> records = recordMapper.selectByIdentity(populationInfo.getId());
            if (records.size() > 0 && records != null) {
                for (CertificationRecord record : records) {
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
                    //删除电子围栏信息
                    Long recordId = record.getId();
                    imsiMapper.deleteByRecordId(recordId);
                    wifiMapper.deleteByRecordId(recordId);

                }
            } else {
                logger.warn("这是不可能存在的");
            }
            //删除所有的3D模板
            templatesMapper.deleteByPid(persons.getPid());
            personsMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public int personsUpdate(Persons persons) {
        return  personsMapper.updateByPrimaryKeyWithBLOBs(persons);
    }

}
