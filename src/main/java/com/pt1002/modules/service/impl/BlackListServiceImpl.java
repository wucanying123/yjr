package com.pt1002.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pt1002.common.exceptions.ApplicationException;
import com.pt1002.modules.mapper.BlackListMapper;
import com.pt1002.modules.mapper.DeviceBlackMapper;
import com.pt1002.modules.mapper.DevicesMapper;
import com.pt1002.modules.pojo.*;
import com.pt1002.modules.service.BlackListService;
import com.pt1002.util.MD5Util;
import com.pt1002.util.UUIDWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class BlackListServiceImpl implements BlackListService {
    @Resource
    private BlackListMapper blackListMapper;
    @Resource
    private DeviceBlackMapper deviceBlackMapper;
    @Resource
    private DevicesMapper devicesMapper;

    @Override
    public Map<String, Object> query(String name, String idcard, Integer deviceId, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        BlackListExample example = new BlackListExample();
        BlackListExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (StringUtils.isNotEmpty(idcard)) {
            criteria.andIdcardLike("%" + idcard + "%");
        }
        if (deviceId != null && deviceId > 0) {
            DeviceBlackExample deviceBlackExample = new DeviceBlackExample();
            deviceBlackExample.createCriteria().andDeviceIdEqualTo(deviceId).andStateNotEqualTo((short)3);
            List<DeviceBlack> deviceBlacks = deviceBlackMapper.selectByExample(deviceBlackExample);
            if (deviceBlacks != null && !deviceBlacks.isEmpty()) {
                List<Integer> ids = new ArrayList<>();
                deviceBlacks.forEach(deviceBlack -> ids.add(deviceBlack.getBlackId()));
                criteria.andIdNotIn(ids);
            }
        }

        List<BlackList> blackLists = blackListMapper.selectByExample(example);
        PageInfo<BlackList> info = new PageInfo<>(blackLists);
        Map<String, Object> rs = new HashMap<>();
        int count = (int) info.getTotal();
        rs.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        rs.put("rows", info.getList());
        rs.put("records", count);
        rs.put("page", page);
        return rs;
    }

    @Override
    public int addBlackList(BlackList blackList) throws ApplicationException {
        if (StringUtils.isEmpty(blackList.getIdcard()) || StringUtils.isEmpty(blackList.getName()))
            throw new ApplicationException("参数错误");
        BlackListExample blackListExample = new BlackListExample();
        blackListExample.createCriteria().andIdcardEqualTo(blackList.getIdcard());
        int count = blackListMapper.countByExample(blackListExample);
        if (count > 0)
            throw new ApplicationException("该身份证号已经加入黑名单了，无需添加");
        int i = blackListMapper.insertSelective(blackList);
        return i;
    }

    @Override
    public int deleteById(List<Integer> id) {
        DeviceBlackExample deviceBlackExample = new DeviceBlackExample();
        deviceBlackExample.createCriteria().andBlackIdIn(id);
        List<DeviceBlack> deviceBlacks = deviceBlackMapper.selectByExample(deviceBlackExample);
        if (deviceBlacks != null && !deviceBlacks.isEmpty()) {
            List<Integer> deviceIds = new ArrayList<>();
            for (DeviceBlack deviceBlack : deviceBlacks) {
                deviceIds.add(deviceBlack.getDeviceId());
            }
            DeviceBlack deviceBlack = new DeviceBlack();
            deviceBlack.setState((short) 3);
            //删除该设备对应的黑名单,假删除，只有等设备请求更新完毕后才做物理删除
            deviceBlackMapper.updateByExampleSelective(deviceBlack, deviceBlackExample);
        }
        BlackListExample example = new BlackListExample();
        example.createCriteria().andIdIn(id);
        int i = blackListMapper.deleteByExample(example);
        return i;
    }

    @Override
    public int updateById(BlackList blackList) throws ApplicationException {
        if (StringUtils.isEmpty(blackList.getIdcard()) || StringUtils.isEmpty(blackList.getName()))
            throw new ApplicationException("参数错误");
        int i = blackListMapper.updateByPrimaryKeySelective(blackList);
        return i;
    }

    @Override
    public Map<String, Object> queryDeviceBlackList(String name, String idcard, Integer deviceId, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<DeviceBlackDTO> deviceBlackDTOS = deviceBlackMapper.selectDeivceBlackByCondition(name, idcard, deviceId);
        PageInfo<DeviceBlackDTO> pageInfo = new PageInfo<>(deviceBlackDTOS);
        Map<String, Object> rs = new HashMap<>();
        int count = (int) pageInfo.getTotal();
        rs.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        rs.put("rows", pageInfo.getList());
        rs.put("records", count);
        rs.put("page", page);
        return rs;
    }

    @Override
    public int putBlackList2Deveice(Integer deviceId, List<Integer> blackId) throws ApplicationException {
        if (deviceId == null || deviceId < 0 || blackId == null || blackId.isEmpty())
            throw new ApplicationException("参数错误");
        int count = 0;
        for (Integer arg : blackId) {
            if (checkCanPut(arg, deviceId)) {
                DeviceBlack deviceBlack = new DeviceBlack();
                deviceBlack.setBlackId(arg);
                deviceBlack.setDeviceId(deviceId);
                deviceBlack.setState((short) 1);
                count += deviceBlackMapper.insertSelective(deviceBlack);
            }
        }
        return count;
    }

    @Override
    public int deleteDeviceBlack(List<Integer> ids) {
        DeviceBlackExample deviceBlackExample = new DeviceBlackExample();
        deviceBlackExample.createCriteria().andIdIn(ids);
        DeviceBlack deviceBlack = new DeviceBlack();
        deviceBlack.setState((short) 3);
        int i = deviceBlackMapper.updateByExampleSelective(deviceBlack, deviceBlackExample);
        return i;
    }

    @Override
    public int addBlackList(String name, String idcard) throws ApplicationException {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(idcard))
            throw new ApplicationException("参数错误");
        BlackListExample blackListExample = new BlackListExample();
        blackListExample.createCriteria().andIdcardEqualTo(idcard);
        int i = blackListMapper.countByExample(blackListExample);
        if (i > 0)
            throw new ApplicationException("该身份证号已经加入黑名单了，无需添加");
        BlackList blackList = new BlackList();
        blackList.setIdcard(idcard);
        blackList.setName(name);
        return blackListMapper.insertSelective(blackList);
    }

    @Override
    public boolean checkCanPushBySn(String sn) throws ApplicationException {
        if (StringUtils.isEmpty(sn))
            throw new ApplicationException("参数错误");
        Devices devices = devicesMapper.selectBySn(MD5Util.getMD5(sn));
//        有则获取，无则添加
        if (devices == null) {
            devices = new Devices();
            int length = sn.length();
            if (length == 16) {
                devices.setName("设备-" + sn.substring(3, 6) + sn.substring(11));
            } else {
                devices.setName("设备-" + (sn.length()-8<=0?sn:sn.substring(sn.length()-8,sn.length())));
            }
            devices.setSn(MD5Util.getMD5(sn));
            devices.setCreateTime(new Date());
            devices.setLatitude("");
            devices.setLongitude("");
            devices.setStationId("");
            devices.setStationName("");
            devices.setCanPush(false);
            devicesMapper.insertSelective(devices);
            return false;
        } else {
            return devices.getCanPush();
        }
    }

    @Override
    public List<PersonDTO> queryBlackListBySn(String sn) throws ApplicationException {
        if (StringUtils.isEmpty(sn))
            throw new ApplicationException("参数错误");
        DevicesExample devicesExample = new DevicesExample();
        devicesExample.createCriteria().andSnEqualTo(MD5Util.getMD5(sn)).andCanPushEqualTo(true);
        int count = devicesMapper.countByExample(devicesExample);
        if (count == 0)
            throw new ApplicationException("该设备无需更新");
        List<PersonDTO> personDTOS = deviceBlackMapper.selectBySn(MD5Util.getMD5(sn));
        Devices devices = new Devices();
        devices.setCanPush(false);
        devicesMapper.updateByExampleSelective(devices, devicesExample);

        return personDTOS;
    }

    @Override
    public int setCanPush(Integer deviceId) throws ApplicationException {
        DeviceBlackExample deviceBlackExample = new DeviceBlackExample();
        deviceBlackExample.createCriteria().andDeviceIdEqualTo(deviceId).andStateNotEqualTo((short)2);
        int count = deviceBlackMapper.countByExample(deviceBlackExample);
        if(count==0){
            throw new ApplicationException("当前状态无需更新");
        }else{
            //物理删除状态为3的黑名单，避免占空间
            deviceBlackExample = new DeviceBlackExample();
            deviceBlackExample.createCriteria().andDeviceIdEqualTo(deviceId).andStateEqualTo((short) 3);
            deviceBlackMapper.deleteByExample(deviceBlackExample);
            //将状态为1的修改为2，方便下次判断能否更新
            deviceBlackExample = new DeviceBlackExample();
            deviceBlackExample.createCriteria().andDeviceIdEqualTo(deviceId).andStateEqualTo((short)1);
            DeviceBlack deviceBlack = new DeviceBlack();
            deviceBlack.setState((short)2);
            deviceBlackMapper.updateByExampleSelective(deviceBlack,deviceBlackExample);

            Devices devices = new Devices();
            devices.setId(deviceId);
            devices.setCanPush(true);
            return devicesMapper.updateByPrimaryKeySelective(devices);
        }
    }


    @Override
    public int batchAdd(List<BlackList> blackLists){
        int count = 0;
        for(BlackList blackList:blackLists){
            if(StringUtils.isNotEmpty(blackList.getName())&&StringUtils.isNotEmpty(blackList.getIdcard()))
                count += blackListMapper.insertSelective(blackList);
        }
        return count;
    }


    private boolean checkCanPut(int blackId, int deviceId) {
        DeviceBlackExample example = new DeviceBlackExample();
        example.createCriteria().andBlackIdEqualTo(blackId).andDeviceIdEqualTo(deviceId).andStateNotEqualTo((short)3);
        int i = deviceBlackMapper.countByExample(example);
        return i == 0;
    }
}
