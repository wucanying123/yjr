package com.pt1002.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pt1002.common.exceptions.ApplicationException;
import com.pt1002.common.exceptions.ParamException;
import com.pt1002.modules.mapper.*;
import com.pt1002.modules.pojo.*;
import com.pt1002.modules.pojo.query.PopInfoQuery;
import com.pt1002.modules.pojo.strong.AndroidStrong;
import com.pt1002.modules.pojo.strong.PopRecord;
import com.pt1002.modules.service.PopulationInfoService;
import com.pt1002.common.PropertiesConstant;

import com.pt1002.modules.service.UploadRecordService;
import com.pt1002.util.ApplicationConstant;
import com.pt1002.util.MD5Util;
import com.pt1002.util.UUIDWorker;
import com.pt1002.util.UploadFileUtil;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Transactional(rollbackFor = Exception.class)
public class PopulationInfoServiceImpl implements PopulationInfoService {

    private static final Logger logger = LogManager.getLogger(PopulationInfo.class);

    private ExecutorService executors = Executors.newFixedThreadPool(2);

    @Resource
    PopulationInfoMapper populationInfoMapper;

    @Resource
    CertificationRecordMapper recordMapper;

    @Resource
    TemplatesMapper templatesMapper;

    @Resource
    DevicesMapper devicesMapper;

    @Resource
    PersonsMapper personsMapper;

    @Resource
    WifiMapper wifiMapper;

    @Resource
    ImsiMapper imsiMapper;

    @Resource
    FaceMapper faceMapper;

    @Resource
    BlackListMapper blackListMapper;

    @Resource
    DeviceBlackMapper deviceBlackMapper;

    @Autowired
    UploadRecordService uploadRecordService;

    @Override
    public int upload(AndroidStrong data, MultipartFile sceneFile, MultipartFile identityFile, MultipartFile template, MultipartFile hi_res, MultipartFile hi_res_tex, MultipartFile photoFile) throws ApplicationException, ParamException {

        String identityCard = "";
        //根据人传入的记录进行新增的记录
        if (data.getPopulationInfo() != null && data.getPopulationInfo().getIdentityCard() != null) {
            identityCard = data.getPopulationInfo().getIdentityCard();
            PopulationInfo populationInfo = populationInfoMapper.selectByIdentity(identityCard);
            if (populationInfo == null) {
                populationInfo = data.getPopulationInfo();
                populationInfoMapper.insertSelective(populationInfo);
            } else {
                logger.warn("人的身份已经存在");
            }
        }


        //sn的封装  如果有这个DEVICES设备就增加
        String sn = data.getSn();
        String md5 = MD5Util.getMD5(sn);
        Devices devices = devicesMapper.selectBySn(md5);
        if (devices == null) {
            devices = new Devices();
            int length = sn.length();
            if(length==16){
                devices.setName("设备-"+sn.substring(3,6)+sn.substring(11));
            }else {
                devices.setName("设备："+UUIDWorker.random());
            }
            devices.setSn(MD5Util.getMD5(sn));
            devices.setCreateTime(new Date());
            devices.setCanPush(false);
            devicesMapper.insertSelective(devices);
        } else {
            logger.warn("已存在SN");
        }
        String sceneImage = null;
        //上传场景文件
        if (sceneFile != null) {
            sceneImage = UploadFileUtil.uploadImage(PropertiesConstant.SCENE_PATH, sceneFile);

            if (sceneImage == null) {
                logger.info("  场景照片文件上传失败，错误原因：返回为null，请检查代码逻辑");
                throw new ApplicationException("场景照片上传失败");
            } else if (ApplicationConstant.FILE_FORMAT_ERROR.equals(sceneImage)) {
                logger.info("  场景照片文件上传失败，错误原因：图片格式不正确");
                throw new ApplicationException("场景照片格式不对");
            }
        }

        String pictrueImage = null;
        //上传抓拍图文件   上传的文件保存在哪里
        if (identityFile != null) {
            pictrueImage = UploadFileUtil.uploadImage(PropertiesConstant.IDENTITY_PATH, identityFile);
            if (pictrueImage == null) {
                logger.info("身份照片文件上传失败，错误原因：返回为null，请检查代码逻辑");
                throw new ApplicationException("身份照片上传失败");
            } else if (ApplicationConstant.FILE_FORMAT_ERROR.equals(sceneImage)) {
                logger.info("  身份照片文件上传失败，错误原因：图片格式不正确");
                throw new ApplicationException("身份照片格式不对");
            }
        }
        String photoImage = null;
        //上传图文件   上传的文件保存在哪里
        if (photoFile != null) {
            photoImage = UploadFileUtil.uploadImage(PropertiesConstant.TEMPLATES_PATH, photoFile);
            if (photoImage == null) {
                logger.info("模板照片文件上传失败，错误原因：返回为null，请检查代码逻辑");
                throw new ApplicationException("模板照片上传失败");
            } else if (ApplicationConstant.FILE_FORMAT_ERROR.equals(sceneImage)) {
                logger.info("  模板照片文件上传失败，错误原因：图片格式不正确");
                throw new ApplicationException("模板照片格式不对");
            }
        }


        //认证记录的增加
        CertificationRecord record = new CertificationRecord();
        PopulationInfo pop = populationInfoMapper.selectByIdentity(identityCard);
        record.setIdentityId(pop.getId());
        record.setScore(Float.parseFloat(data.getPopulationInfo().getScore()));
        record.setDevicesId(devices.getId().longValue());
        if (sceneImage != null) {
            record.setScenePath(PropertiesConstant.SERVER_PATH + "/pt1002" + sceneImage);
        }
        if (pictrueImage != null) {
            record.setPicturePath(PropertiesConstant.SERVER_PATH + "/pt1002" + pictrueImage);
        }
        record.setCreateTime(data.getPopulationInfo().getCreateTime());
        System.out.println(data.getPopulationInfo().getCreateTime());
        record.setProvesuccessful(data.getPopulationInfo().getProveSuccessful());
        record.setCollectsuccessful(data.getPopulationInfo().getCollectSuccessful());

        recordMapper.insertSelective(record);
        if (record.getId() != null) {
            logger.warn("插入还回主键  暂时成功");
        }

        //分装进来persons  还有需要填充的数据

        Persons persons1 = new Persons();
        if (photoImage != null) {
            persons1.setPhotoPath(PropertiesConstant.SERVER_PATH + "/pt1002" + photoImage);
        }

        persons1.setFirstName(pop.getName());
        persons1.setBirth(pop.getBirthday());
        if (pop.getSex().equals("男")) {
            persons1.setSex("male");
        } else if (pop.getSex().equals("女")) {
            persons1.setSex("female");
        }
        persons1.setCardId(pop.getId().toString());


        //将其余的三个文件转成byte数组  封装进3D模板类
        try {
            if (data.getATCparameters() != null) {
                personsMapper.insertSelective(persons1);
                TemplatesWithBLOBs bs = new TemplatesWithBLOBs();
                if (template != null) {
                    byte[] templateBytes = template.getBytes();
                    bs.setTemplate(templateBytes);
                }
                if (hi_res != null) {
                    byte[] hiResBytes = hi_res.getBytes();
                    bs.setHiRes(hiResBytes);
                }
                if (hi_res_tex != null) {
                    byte[] hiResTexBytes = hi_res_tex.getBytes();
                    bs.setHiResTex(hiResTexBytes);
                }
                Templates atc = data.getATCparameters();
                bs.setDeviceSn(atc.getDeviceSn());
                bs.setDate(new Date());
                bs.setEnrDist(atc.getEnrDist());
                bs.setMark(atc.getMark());
                bs.setQuality(atc.getQuality());
                bs.setPid(persons1.getPid());

                if (record.getId() == null)
                    throw new ParamException("认证记录插入失败");
                bs.setUid(record.getId().toString());
                templatesMapper.insertSelective(bs);
            }
        } catch (IOException e) {
            logger.warn("封装3D模板失败");
            e.printStackTrace();
        }


        //  封装电子围栏的信息
        if (data.getElectronic() != null && data.getElectronic().getIMSI() != null) {
            List<Imsi> imsi = data.getElectronic().getIMSI();
            if (imsi != null && imsi.size() > 0) {
                for (Imsi imsi1 : imsi) {
                    imsi1.setRecordId(record.getId());
                    imsiMapper.insertSelective(imsi1);
                }
            } else {
                logger.warn("没有IMSI的数据");
            }

        }

        List<Wifi> wifis = new ArrayList<>();
        if (data.getElectronic() != null && data.getElectronic().getWIFI() != null) {
            List<Wifi> wifi = data.getElectronic().getWIFI();
            if (wifi.size() > 0 && wifi != null) {
                for (Wifi wifi1 : wifi) {
                    wifi1.setRecordId(record.getId());
                    wifis.add(wifi1);
                    wifiMapper.insertSelective(wifi1);
                }
            } else {
                logger.warn("没有WIFI的数据");
            }
        }

        final String sceneFilePath = sceneImage;
        final String identityFilePath = pictrueImage;
        //上传认证记录到第三方平台
        CompletableFuture.supplyAsync( () -> {
            assert sceneFilePath != null;
            assert identityFilePath != null;
            return uploadRecordService.insertUploadRecord(data.getPopulationInfo(), data.getSn(), record, wifis, sceneFilePath, identityFilePath);
        }, executors);

        return 1;
    }

    @Override
    public int deletePopInfo(String ids) throws ApplicationException {
        String[] split = ids.split(",");
        int count = 0;
        for (String s : split) {
            long id = Long.valueOf(s);

            PopulationInfo info = populationInfoMapper.selectByPrimaryKey(id);

            //删除档案记录
            List<Persons> persons = personsMapper.selectByCardId(id);

            for (Persons person : persons) {
//                String photoPath = person.getPhotoPath();
//                String substring = photoPath.substring(photoPath.lastIndexOf("/"));
//                if (photoPath != null) {
//                    File file = new File(PropertiesConstant.getTemplatesPath() + substring);
//                    if (file.exists()) {
//                        file.delete();
//                    }
//                } else {
//                    logger.warn("没有抓拍图");
//                }

                personsMapper.deleteByPrimaryKey(person.getPid());
                //删除3D模板信息
                templatesMapper.deleteByPid(person.getPid());


            }

            //  找寻出记录删除所有的图片
            List<CertificationRecord> records = recordMapper.selectByIdentity(id);
            if (records.size() > 0 && records != null) {
                for (CertificationRecord record : records) {
                    String picturePath = record.getPicturePath();
                    String picture = picturePath.substring(picturePath.lastIndexOf("/"));
                    String scenePath = record.getScenePath();
                    String scene = scenePath.substring(scenePath.lastIndexOf("/"));
                    if (picturePath != null) {
                        File file = new File(PropertiesConstant.IDENTITY_PATH + picture);
                        if (file.exists()) {
                            file.delete();
                        }
                    } else {
                        logger.warn("没有抓拍图");
                    }
                    if (scenePath != null) {
                        File file = new File(PropertiesConstant.SCENE_PATH + scene);
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

            //删除数据库的所有的记录

            recordMapper.deleteByIdentityId(id);

            //删除对应的黑名单信息
            BlackListExample blackListExample = new BlackListExample();
            blackListExample.createCriteria().andNameEqualTo(info.getName()).andIdcardEqualTo(info.getIdentityCard());
            List<BlackList> blackLists = blackListMapper.selectByExample(blackListExample);
            if(blackLists!=null&&!blackLists.isEmpty()){
                List<Integer> blackIds = new ArrayList();
                for(BlackList blackList:blackLists){
                    blackIds.add(blackList.getId());
                }
                DeviceBlackExample deviceBlackExample = new DeviceBlackExample();
                deviceBlackExample.createCriteria().andBlackIdIn(blackIds);

                List<DeviceBlack> deviceBlacks = deviceBlackMapper.selectByExample(deviceBlackExample);
                if(deviceBlacks!=null&&!deviceBlacks.isEmpty()){
                    List<Integer> deviceIds = new ArrayList<>();
                    for(DeviceBlack deviceBlack:deviceBlacks){
                        deviceIds.add(deviceBlack.getDeviceId());
                    }
                    //TODO  删除该设备对应的黑名单(软删除)
                    DeviceBlack deviceBlack = new DeviceBlack();
                    deviceBlack.setState((short)3);
                    deviceBlackMapper.updateByExampleSelective(deviceBlack,deviceBlackExample);
                }
                blackListMapper.deleteByExample(blackListExample);
            }
            count += populationInfoMapper.deleteByPrimaryKey(id);


        }
        return count;
    }

    @Override
    public int updatePopInfo(PopulationInfo populationInfo) {
        return populationInfoMapper.updateByPrimaryKeySelective(populationInfo);
    }

    @Override
    public int addPopInfo(PopulationInfo popRecord) throws ParamException {


        //分离数据进行封装
        if (popRecord.getExpireDateEnd() == null || popRecord.getBirthday() == null || popRecord.getExpireDateStart() == null) {
            logger.warn("参数格式出错");
            throw new ParamException();
        }
        return populationInfoMapper.insertSelective(popRecord);
    }

    @Override
    public PopInfoQuery findPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<PopulationInfo> populationInfos = populationInfoMapper.selectByExample(null);
        PageInfo<PopulationInfo> info = new PageInfo<>(populationInfos);
        PopInfoQuery infoQuery = new PopInfoQuery();
        infoQuery.setRecords(info.getTotal());
        infoQuery.setPage(Long.valueOf(page));
        infoQuery.setTotal(info.getTotal() % Long.valueOf(rows) == 0 ? info.getTotal() / Long.valueOf(rows) : info.getTotal() / Long.valueOf(rows) + 1);
        infoQuery.setRows(populationInfos);
        return infoQuery;
    }

    @Override
    public List<PopulationInfo> findIdentityId() {
        List<PopulationInfo> populationInfos = populationInfoMapper.selectByExample(null);
        return populationInfos;
    }

    @Override
    public PopInfoQuery popHistory(Long id, Long page, Long rows) {

        PersonsExample personsExample = new PersonsExample();
        PersonsExample.Criteria criteria1 = personsExample.createCriteria();
        criteria1.andCardIdEqualTo(id.toString());

        List<PopRecord> list = new ArrayList<>();

        PageHelper.startPage(page.intValue(), rows.intValue());
        List<Persons> persons = personsMapper.selectByExample(personsExample);
        PageInfo<Persons> info = new PageInfo<>(persons);

        List<TemplatesWithBLOBs> tBLOBs = new ArrayList<>();
        List<TemplatesWithBLOBs> tob = null;
        for (Persons person : persons) {
            TemplatesExample templatesExample = new TemplatesExample();
            TemplatesExample.Criteria criteria = templatesExample.createCriteria();
            criteria.andPidEqualTo(person.getPid());
            tob = templatesMapper.selectByExampleWithBLOBs(templatesExample);
            if (tob != null && tob.size() > 0) {
                tBLOBs.add(tob.get(0));
            }
        }

        for (int i = 0; i < persons.size(); i++) {
            PopRecord popRecord = new PopRecord();

            if (tBLOBs.size() - 1 >= i) {
                popRecord.settBloBs(tBLOBs.get(i));
            } else {
                popRecord.settBloBs(null);
            }

            if (persons.size() - 1 >= i) {
                popRecord.setPersons(persons.get(i));
            } else {
                popRecord.setPersons(null);
            }
            list.add(popRecord);
        }
        PopInfoQuery<PopRecord> infoQuery = new PopInfoQuery<>();
        infoQuery.setRows(list);
        infoQuery.setRecords(info.getTotal());
        infoQuery.setPage(page);
        infoQuery.setTotal(info.getTotal() % rows == 0 ? info.getTotal() / rows : info.getTotal() / rows + 1);
        return infoQuery;
    }

    @Override
    public PopulationInfo findPop(String identityId) {
        PopulationInfoExample infoExample = new PopulationInfoExample();
        PopulationInfoExample.Criteria criteria = infoExample.createCriteria();
        criteria.andIdentityCardEqualTo(identityId);
        List<PopulationInfo> infos = populationInfoMapper.selectByExample(infoExample);
        return infos.get(0);
    }


}