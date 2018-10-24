package com.pt1002.modules.service.impl;

import com.pt1002.common.constant.ThirdPartyConst;
import com.pt1002.common.entity.UploadResp;
import com.pt1002.common.exceptions.DataNotFoundException;
import com.pt1002.common.exceptions.ParamException;
import com.pt1002.modules.mapper.*;
import com.pt1002.modules.pojo.*;
import com.pt1002.modules.service.DevicesService;
import com.pt1002.modules.service.UploadRecordService;
import com.pt1002.modules.service.WifiService;
import com.pt1002.spring.props.ThirdPartyProp;
import com.pt1002.util.HttpUtil;
import com.pt1002.util.UUIDWorker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: xubo
 * @Description:
 * @Date: Create in 15:56 2018/6/8
 * @Modified By:
 * @Test By:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UploadRecordServiceImpl implements UploadRecordService{

    private static final Logger logger = LoggerFactory.getLogger(UploadRecordServiceImpl.class);

    @Autowired
    ThirdPartyProp thirdPartyProp;

    @Resource
    UploadConfigMapper uploadConfigMapper;

    @Resource
    DevicesService devicesService;

    @Resource
    UploadRecordMapper uploadRecordMapper;

    @Resource
    CertificationRecordMapper certificationRecordMapper;

    @Resource
    DevicesMapper devicesMapper;

    @Resource
    PopulationInfoMapper populationInfoMapper;

    @Autowired
    UploadRecordService uploadRecordService;

    @Autowired
    WifiService wifiService;

    @Override
    public UploadConfig queryUploadConfig(){
        return uploadConfigMapper.selectByPrimaryKey(1);
    }

    @Override
    public int updateUploadConfig(String ip, int port, int macPort) throws ParamException{

        if (StringUtils.isEmpty(ip) || port < 1)
            throw new ParamException();
        UploadConfig uploadConfig = new UploadConfig();
        uploadConfig.setId(1);
        uploadConfig.setIp(ip);
        uploadConfig.setPort(port);
        uploadConfig.setMacPort(macPort);
        return uploadConfigMapper.updateByPrimaryKeySelective(uploadConfig);
    }

    @Override
    public UploadRecord findUploadRecordByCertId(long certificationId) {

        UploadRecordExample example = new UploadRecordExample();
        example.createCriteria().andCertificationIdEqualTo(certificationId);
        List<UploadRecord> uploadRecords = uploadRecordMapper.selectByExample(example);
        if (uploadRecords != null && uploadRecords.size() > 0){
            return uploadRecords.get(0);
        }
        return null;
    }

    @Override
    public int insertUploadRecord(PopulationInfo populationInfo,
                                   String devicesSn,
                                   CertificationRecord certificationRecord,
                                   List<Wifi> wifis,
                                   String sceneFile,
                                   String identityFile) {

        Devices device = devicesService.findBySn(devicesSn);
        if (device == null ){
            logger.warn("上传数据到第三方平台时查询不到设备");
            return -1;
        }
        //上传记录到第三方平台
        String guid = UUIDWorker.random();
        int state = 0;
        UploadResp fileUpResp = doUploadFile(guid, populationInfo.getIdentityCard(), new File(identityFile), new File(sceneFile));
        if (fileUpResp == null || !fileUpResp.isSuccess()){
            //文件上传失败
            logger.warn("上传文件到第三方平台失败");
        }else {
            //在上传文件成功的前提下上传认证数据
            state = 1;
            UploadResp dataUpResp = doUploadData(guid, fileUpResp.getZfsPath(), populationInfo, device, certificationRecord);
            if (dataUpResp != null && dataUpResp.isSuccess()){
                //认证数据上传成功
                state = 2;
            }
        }

        if (wifis != null && wifis.size() > 0){
            UploadResp uploadResp = doUploadMac(wifis, guid);
            if (uploadResp == null || !uploadResp.isSuccess()){
                state = 3;
            }
        }

        UploadRecord uploadRecord = new UploadRecord();
        uploadRecord.setLastUpdate(new Date());
        uploadRecord.setState((byte)state);
        uploadRecord.setRetryTimes(0);
        uploadRecord.setServerInfo(ThirdPartyConst.IP+":"+ThirdPartyConst.PORT);
        uploadRecord.setCertificationId(certificationRecord.getId());
        uploadRecord.setGuid(guid);
        //根据记录上传的成功与否 生成上传日志
        uploadRecordMapper.insert(uploadRecord);

        return 1;
    }

    @Override
    public int reUploadRecord(long certificationId) throws ParamException, DataNotFoundException{

        //查询历史上传记录
        UploadRecord uploadRecord = uploadRecordService.findUploadRecordByCertId(certificationId);
        if (uploadRecord == null){
            //查询不到上传记录，说明是系统更新前的历史记录，需要重新生成上传记录
            uploadRecord = new UploadRecord();
            uploadRecord.setGuid(UUIDWorker.random());
            uploadRecord.setRetryTimes(0);
            uploadRecord.setServerInfo(ThirdPartyConst.IP+":"+ThirdPartyConst.PORT);
            uploadRecord.setLastUpdate(new Date());
//            uploadRecord.setState((byte)0);
        }

        //拿到认证记录的id，根据认证记录的id查询出所有其他的信息
        CertificationRecord certificationRecord = certificationRecordMapper.selectByPrimaryKey(certificationId);
        if (certificationRecord == null){
            logger.warn("重新上传认证记录 id = {} 找不到数据", certificationId);
            throw new ParamException("certificationId "+certificationId+" not found");
        }

        //查询设备信息
        Devices devices = devicesMapper.selectByPrimaryKey(certificationRecord.getDevicesId().intValue());
        if (devices == null){
            logger.warn("重新上传认证记录 id = {} 找不到相应的设备信息", certificationId);
            throw new DataNotFoundException("deviceId "+certificationRecord.getDevicesId()+" not found");
        }

        //身份信息
        PopulationInfo populationInfo = populationInfoMapper.selectByPrimaryKey(certificationRecord.getIdentityId());
        if (populationInfo == null){
            logger.warn("重新上传认证记录 id = {} 找不到相应的认证信息", certificationId);
            throw new DataNotFoundException("population "+certificationRecord.getIdentityId()+" not found");
        }

        //找到相应的图片
        String scenePath = certificationRecord.getScenePath();
        String identityPath = certificationRecord.getPicturePath();
        String sceneFilePath = scenePath.substring(scenePath.indexOf("pt1002")).replace("pt1002","");
        String identityFilePath = identityPath.substring(identityPath.indexOf("pt1002")).replace("pt1002","");
        File sceneFile = new File(sceneFilePath);
        File identityFile = new File(identityFilePath);
        if (!sceneFile.exists() || !identityFile.exists()){
            logger.warn("重新上传认证记录 id = {} 找不到相应的场景图或身份证图片", certificationId);
            throw new DataNotFoundException("scene file "+sceneFilePath+" or identity file "+identityFilePath+" not found");
        }

        int state = 0;
        UploadResp uploadFileResp = doUploadFile(uploadRecord.getGuid(), populationInfo.getIdentityCard(), identityFile, sceneFile);
        if (uploadFileResp != null && uploadFileResp.isSuccess()){
            state = 1;
            UploadResp uploadDataResp = doUploadData(uploadRecord.getGuid(), uploadFileResp.getZfsPath(), populationInfo, devices, certificationRecord);
            if (uploadDataResp != null && uploadDataResp.isSuccess()){
                state = 2;
            }
        }

        //查找相应的mac信息
        List<Wifi> wifis = wifiService.findAll(certificationId);
        if (wifis != null && wifis.size() > 0){
            UploadResp uploadResp = doUploadMac(wifis, uploadRecord.getGuid());
            if (uploadResp == null || !uploadResp.isSuccess()){
                state = 3;
            }
        }

        uploadRecord.setState((byte)state);
        uploadRecord.setCertificationId(certificationId);
        if (uploadRecord.getId() == null || uploadRecord.getId() < 1){
            uploadRecordMapper.insert(uploadRecord);
        }else {
            uploadRecordMapper.updateByPrimaryKey(uploadRecord);
        }
        return state;
    }

    @Override
    public List<Long> findAllNotUploadRecord() {
        return uploadRecordMapper.findAllNotUploadRecord();
    }


    private UploadResp doUploadFile(String guid, String idcard, byte[] identityFile, byte[] sceneFile){
        String desc = "GUID=" + guid + ";" + "CardNo=" + idcard;
        String url = "http://" + ThirdPartyConst.IP + ":" + ThirdPartyConst.PORT + thirdPartyProp.getFileUploadUrl();
        logger.debug("上传文件到第三方平台 url = {}, description = {}", url, desc);

        HashMap<String, byte[]> fileParams = new HashMap<>();

        HashMap<String, String> strParams = new HashMap<>();
        fileParams.put("Card",identityFile);
        fileParams.put("Face", sceneFile);
        fileParams.put("Snapshot", sceneFile);
        strParams.put("Description", desc);
        return HttpUtil.postForm(url, strParams, null, null, fileParams, UploadResp.class);
    }

    private UploadResp doUploadFile(String guid, String idcard, File identityFile, File sceneFile){
        String desc = "GUID=" + guid + ";" + "CardNo=" + idcard;
        String url = "http://" + ThirdPartyConst.IP + ":" + ThirdPartyConst.PORT + thirdPartyProp.getFileUploadUrl();
        logger.info("上传文件到第三方平台 url = {}, description = {}", url, desc);

        HashMap<String, File> fileParams = new HashMap<>();

        HashMap<String, String> strParams = new HashMap<>();
        fileParams.put("Card",identityFile);
        fileParams.put("Face", sceneFile);
        fileParams.put("Snapshot", sceneFile);
        strParams.put("Description", desc);
        return HttpUtil.postForm(url, strParams, fileParams, null, null, UploadResp.class);
    }

    private UploadResp doUploadData(String guid,
                                    String zfsPath,
                                    PopulationInfo populationInfo,
                                    Devices devices,
                                    CertificationRecord certificationRecord){

        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map map = new HashMap();
        map.put("heartbeat"," ");//心跳包
        map.put("PersonId", guid);
        map.put("Name", populationInfo.getName());
        map.put("Sex", populationInfo.getSex());
        map.put("Nation", populationInfo.getNation());
        map.put("Birthday", sdf1.format(populationInfo.getBirthday()));
        map.put("Address", populationInfo.getAddress());
        map.put("CardNo", populationInfo.getIdentityCard());
        map.put("SignGov", populationInfo.getIssue());
        map.put("LimitedDate", sdf0.format(populationInfo.getExpireDateStart()) + " -- " + sdf0.format(populationInfo.getExpireDateEnd()));
        map.put("EntryTime", sdf2.format(certificationRecord.getCreateTime()));
        map.put("Dubious", "0");//该数据为重点人员核验返回结果
        map.put("PositionID", devices.getSn());
        map.put("PositionName", devices.getName());
        float scoreFloat = certificationRecord.getScore()/100;
        map.put("Resemblance", scoreFloat+"");
        map.put("CheckResult", "1");
        map.put("SBJD", StringUtils.isEmpty(devices.getLongitude()) ? " " : devices.getLongitude());
        map.put("SBWD", StringUtils.isEmpty(devices.getLatitude()) ? " " : devices.getLatitude());
        map.put("ZFSPATH", zfsPath);//该数据为图片上传返回结果
        map.put("Note", " ");//该数据为重点人员核验返回结果
        map.put("Nationality", "CHN");
        map.put("CardType", "111");
        map.put("Direction", "1");
        map.put("StationID", StringUtils.isEmpty(devices.getStationId()) ? " " : devices.getStationId());
        map.put("StationName", StringUtils.isEmpty(devices.getStationName()) ? " " : devices.getStationName());
        map.put("Token", UUIDWorker.random());

        String url = "http://" + ThirdPartyConst.IP + ":" + ThirdPartyConst.PORT + thirdPartyProp.getCertificationUploadUrl();

        logger.info("上传认证数据第三方平台 url = {}, jsonBody = {}", url, map);

        return HttpUtil.postJson(url, map, UploadResp.class);
    }

    private UploadResp doUploadMac(List<Wifi> wifis, String guid){

        ArrayList<Map> data = new ArrayList<>();
        wifis.forEach(wifi -> {
            Map item = new HashMap<>();
            item.put("bssid",wifi.getBssid());
            item.put("ssid", wifi.getSsid());
            item.put("channel", wifi.getChannel());
            item.put("climac", wifi.getClimac());
            item.put("devmac", wifi.getDevmac());
            item.put("oui", wifi.getOui());
            item.put("signal", wifi.getSignall());
            item.put("time", wifi.getTime());
            item.put("type", wifi.getType());
            item.put("person_id", guid);
            data.add(item);
        });
        String url = "http://" + ThirdPartyConst.IP + ":" + ThirdPartyConst.PORT + thirdPartyProp.getMacUploadUrl();
        logger.info("上传mac信息到第三方平台 url = {}, jsonBody = {}", url, data);
        return HttpUtil.postJson(url, data, UploadResp.class);
    }

}
